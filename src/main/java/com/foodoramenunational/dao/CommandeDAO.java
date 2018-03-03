/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.dao;

import com.foodoramenunational.modele.Commande;
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
public class CommandeDAO extends DAO<Commande> {

    public CommandeDAO(Connection c) {
        super(c);
    }

    @Override
    public boolean create(Commande x) {
        String req = "INSERT INTO commandes (`id_commande`, `etat_commande`, `items`,  `date_creation`)"
                + "VALUES(?,?,?,?,)";
        PreparedStatement prestm = null;
        try {
            prestm = cnx.prepareStatement(req);
            prestm.setString(1, x.getId_commande());
            prestm.setString(2, x.getEtat_commande());
            prestm.setString(3, x.getItemIds());
            prestm.setDate(4, new java.sql.Date(x.getDate_creation().getTime()));
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
    public boolean delete(Commande x) {
        // TODO Auto-generated method stub
        PreparedStatement prestm = null;
        String req = "UPDATE commandes SET etat_commande = Annuler WHERE id_commande=?";
        try {
            prestm = cnx.prepareStatement(req);
            prestm.setString(1, x.getId_commande());
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
    public Commande read(String id) {
        // TODO Auto-generated method stub
        PreparedStatement prestm = null;
        ResultSet r = null;
        String req = "SELECT * FROM commandes WHERE id_commande= ?";
        ItemDAO itemtDao=new ItemDAO(this.cnx);
        try {
            prestm = cnx.prepareStatement(req);
            prestm.setString(1, id);
            r = prestm.executeQuery();
            while (r.next()) {
                Commande c = new Commande();
                List<Item> list=itemtDao.findAllForCommande(r.getString(3));
                Item[]array=(Item[]) list.toArray();
                c.setId_commande(r.getString(1));
                c.setEtat_commande(r.getString(2));
                c.setItems(array);
                c.setDate_creation(r.getTime(4));
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
    public boolean update(Commande x) {
        // TODO Auto-generated method stub
        PreparedStatement prestm = null;
        String req = "UPDATE commandes SET etat_commande=?, items=?,  date_creation=? WHERE id_commande=? ";
        try {

            prestm = cnx.prepareStatement(req);
            prestm.setString(1, x.getEtat_commande());
            prestm.setString(2, x.getItemIds());
            prestm.setDate(3, new java.sql.Date(x.getDate_creation().getTime()));
            prestm.setString(4, x.getId_commande());

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
    public List<Commande> findAll() {
        List<Commande> liste = new LinkedList<>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM `commandes`  ");
            ItemDAO itemtDao=new ItemDAO(this.cnx);
            while (r.next()) {
                Commande c = new Commande();
                List<Item> list=itemtDao.findAllForCommande(r.getString(3));
                Item[]array=(Item[]) list.toArray();
                c.setId_commande(r.getString(1));
                c.setEtat_commande(r.getString(2));
                c.setItems(array);
                c.setDate_creation(r.getTime(4));
                liste.add(c);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
        }
        return liste;
    }

  

}

