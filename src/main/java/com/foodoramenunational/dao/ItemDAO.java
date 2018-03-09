/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.dao;

import com.foodoramenunational.modele.Ingredient;
import com.foodoramenunational.modele.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kouka
 */
public class ItemDAO extends DAO<Item>{ 

    
    public ItemDAO (Connection c) {
        super(c);
    }

    @Override
    public boolean create(Item x) {
        String req = "INSERT INTO items (`id`, `nom`, `description`, `disponibilite`,`categorie`, `ingredients`, `prix`, `rabais`, `temps-preparation`)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement prestm = null;
        try {
            prestm = cnx.prepareStatement(req);
            prestm.setString(1, x.getId_item());
            prestm.setString(2, x.getNom());
            prestm.setString(3, x.getDescription());
            prestm.setBoolean(4, x.getDisponibilite());
            prestm.setString(5, x.getCategorie());
            prestm.setString(6, x.getIngredient());
            prestm.setDouble(7, x.getPrix());
            prestm.setDouble(8, x.getRabais());
            prestm.setInt(9, x.getTemps_preparation());
            int n = prestm.executeUpdate();
            if (n > 0) {
                prestm.close();
                return true;
            }
        } catch (SQLException exp) {
            String g = exp.getMessage();
            System.out.print(g);
        } finally {
            if (prestm != null) {
                try {
                    prestm.close();

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    @Override
    public boolean delete(Item x) {
        // TODO Auto-generated method stub
        PreparedStatement prestm = null;
        String req = "UPDATE items SET disponibilite = false WHERE id=?";
        try {
            prestm = cnx.prepareStatement(req);
            prestm.setString(1, x.getId_item());
            int n = prestm.executeUpdate();
            if (n > 0) {
                prestm.close();
                return true;
            }
        } catch (SQLException exp) {
        } finally {
            if (prestm != null) {
                try {
                    prestm.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Item read(String id) {
        // TODO Auto-generated method stub
        PreparedStatement prestm = null;
        ResultSet r = null;
        String req = "SELECT * FROM items WHERE id=?";
        IngredientDAO ingredientDao=new IngredientDAO(this.cnx);
        try {
            prestm = cnx.prepareStatement(req);
            prestm.setString(1, id);
            r = prestm.executeQuery();
            while (r.next()) {
                Item c = new Item();
                c.setId_item(r.getString(1));
                c.setNom(r.getString(2));
                c.setDescription(r.getString(3));
                c.setDisponibilite(r.getBoolean(4));
                c.setCategorie(r.getString(5));
                List<Ingredient> list=ingredientDao.findAllForItemMenu(r.getString(6));
                Ingredient[]array=(Ingredient[]) list.toArray();
                c.setIngredient(array);
                c.setPrix(r.getDouble(7));
                c.setRabais(r.getDouble(8));
                c.setTemps_preparation(r.getInt(9));
                return c;
            }
        } catch (SQLException exp) {
            System.out.println("Exception");
        } finally {
            if (prestm != null) {
                try {
                    r.close();
                    prestm.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(Item x) {
        // TODO Auto-generated method stub
        PreparedStatement prestm = null;
        
        String req = "UPDATE items SET nom=?, description=?, disponibilite=?,categorie=?, ingredients=?, prix=?, rabais=?, temps-preparation=? WHERE id_ingredient=?";
        try {

            prestm = cnx.prepareStatement(req);
            prestm.setString(1, x.getNom());
            prestm.setString(2, x.getDescription());
            prestm.setBoolean(3, x.getDisponibilite());
            prestm.setString(4, x.getCategorie());
            prestm.setString(5, x.getIngredient());
            prestm.setDouble(6, x.getPrix());
            prestm.setDouble(7, x.getRabais());
            prestm.setInt(8, x.getTemps_preparation());
            prestm.setString(9, x.getId_item());
            int n = prestm.executeUpdate();
            String g = prestm.toString();
            if (n > 0) {
                prestm.close();
                return true;
            }
        } catch (SQLException exp) {
            String g = exp.getMessage();
            System.out.println(exp);
        } finally {
            if (prestm != null) {
                try {
                    prestm.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        List<Item> liste = new LinkedList<>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM `items`  ");
            IngredientDAO ingredientDao=new IngredientDAO(this.cnx);
            while (r.next()) {
                Item c = new Item();
                c.setId_item(r.getString(1));
                c.setNom(r.getString(2));
                c.setDescription(r.getString(3));
                c.setDisponibilite(r.getBoolean(4));
                c.setCategorie(r.getString(5));
                List<Ingredient> list=ingredientDao.findAllForItemMenu(r.getString(6));
                Ingredient[]array=(Ingredient[]) list.toArray();
                c.setIngredient(array);
                c.setPrix(r.getDouble(7));
                c.setRabais(r.getDouble(8));
                c.setTemps_preparation(r.getInt(9));
                liste.add(c);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
        }
        return liste;
    }

    public List<Item> findAllForCommande(String itemIds) {
        List<Item> liste = new LinkedList<>();
        String [] listItem=itemIds.split(",");
        for (int i=0;i<listItem.length;i++)
            liste.add(read(listItem[i]));
        return liste;
    }

    
}
