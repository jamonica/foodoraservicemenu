/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.modele;

import java.util.Date;

/**
 *
 * @author kouka
 */
public class Commande 
{
    private String id_commande;
    private String etat_commande; 
    private Item [] items;
    private Date date_creation;

    public Commande() {
    }

    public Commande(String id_commande, String etat_commande, Item [] items, Date date_creation) {
        this.id_commande = id_commande;
        this.etat_commande = etat_commande;
        this.items = items;
        this.date_creation = date_creation;
    }

    public String getId_commande() {
        return id_commande;
    }

    public void setId_commande(String id_commande) {
        this.id_commande = id_commande;
    }

    public String getEtat_commande() {
        return etat_commande;
    }

    public void setEtat_commande(String etat_commande) {
        this.etat_commande = etat_commande;
    }

    public Item[] getItems() {
        return items;
    }
    public String getItemIds() {
        String items = "";
        for (int i = 0; i < (this.items.length - 1); i++) {
            items += this.items[i].getId_item() + ",";
        }
        items += this.items[this.items.length - 1];
        return items; 
    }
    public void setItems(Item[] items) {
        this.items = items;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }
    
    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", etat_commande=" + etat_commande + ", items=" + items + ", date_creation=" + date_creation + '}';
    }
    
    
    
}

