/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.dao;

import com.foodoramenunational.modele.Ingredient;
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
public class IngredientDAO extends DAO<Ingredient>{ 
    
    public IngredientDAO(Connection c) {
        super(c);
    }

    @Override
    public boolean create(Ingredient x) {
        String req = "INSERT INTO ingredient (`id_ingredient`, `nom`, `disponibilite`)"
                + "VALUES(?,?,?)";
        PreparedStatement prestm = null;
        try {
            prestm = cnx.prepareStatement(req);
            prestm.setString(1, x.getId_ingredient());
            prestm.setString(2, x.getNom());
            prestm.setBoolean(3, x.getDisponibilite());
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
    public boolean delete(Ingredient x) {
        // TODO Auto-generated method stub
        PreparedStatement prestm = null;
        String req = "UPDATE ingredients SET disponibilite = false WHERE id_ingredient=?";
        try {
            prestm = cnx.prepareStatement(req);
            prestm.setString(1, x.getId_ingredient());
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
    public Ingredient read(String id) {
        // TODO Auto-generated method stub
        PreparedStatement prestm = null;
        ResultSet r = null;
        String req = "SELECT * FROM ingredients WHERE id_ingredient=?";
        try {
            prestm = cnx.prepareStatement(req);
            prestm.setString(1, id);
            r = prestm.executeQuery();
            while (r.next()) {
                Ingredient c = new Ingredient();
                c.setId_ingredient(r.getString(1));
                c.setNom(r.getString(2));
                c.setDisponibilite(r.getBoolean(3));
                prestm.close();
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
    public boolean update(Ingredient x) {
        // TODO Auto-generated method stub
        PreparedStatement prestm = null;
        
        String req = "UPDATE ingredients SET disponibilite = ? ,nom=? WHERE id_ingredient=?";
        try {

            prestm = cnx.prepareStatement(req);
            prestm.setBoolean(1, x.getDisponibilite());
            prestm.setString(2, x.getNom());
            prestm.setString(3, x.getId_ingredient());
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
    public List<Ingredient> findAll() {
        List<Ingredient> liste = new LinkedList<>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM `ingredients`  ");
            while (r.next()) {
                Ingredient c = new Ingredient();
                c.setId_ingredient(r.getString(1));
                c.setDisponibilite(r.getBoolean(2));
                c.setNom(r.getString(3));
                liste.add(c);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
        }
        return liste;
    }

    public List<Ingredient> findAllForItemMenu(String ingredientIds) {
        List<Ingredient> liste = new LinkedList<>();
        String [] listIngredient=ingredientIds.split(",");
        for (int i=0;i<listIngredient.length;i++)
            liste.add(read(listIngredient[i]));
        return liste;
    }

    
}
