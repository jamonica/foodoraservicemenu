/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.modele;

import java.util.List;

/**
 *
 * @author kouka
 */
public class Item
{
        private String id_item;
        private String nom;
        private String description;
        private String categorie;
        private List <Ingredient>ingredients;
        private Boolean disponibilite;
        private double prix;
        private double rabais;
        private int temps_preparation;

    public Item() {
    }

    public Item(String id_item, String nom, String description, String categorie, List <Ingredient> ingredients, Boolean disponibilite, double prix, double rabais, int temps_preparation) {
        this.id_item = id_item;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.ingredients = ingredients;
        this.disponibilite = disponibilite;
        this.prix = prix;
        this.rabais = rabais;
        this.temps_preparation = temps_preparation;
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /*public Ingredient[] getIngredient() {
        return ingredients;
    }*/

    public void setIngredient(List <Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getRabais() {
        return rabais;
    }

    public void setRabais(double rabais) {
        this.rabais = rabais;
    }

    public int getTemps_preparation() {
        return temps_preparation;
    }

    public void setTemps_preparation(int temps_preparation) {
        this.temps_preparation = temps_preparation;
    }

    @Override
    public String toString() {
        return "Items{" + "id_item=" + id_item + ", nom=" + nom + ", description=" + description + ", categorie=" + categorie + ", ingredients=" + ingredients + ", disponibilite=" + disponibilite + ", prix=" + prix + ", rabais=" + rabais + ", temps_preparation=" + temps_preparation + '}';
    }
    
    
    
}


