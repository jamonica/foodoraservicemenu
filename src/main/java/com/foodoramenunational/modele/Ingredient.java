/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.modele;
/**
 *
 * @author kouka
 */
public class Ingredient {
    
   private  String id_ingredient ;
   private  String  nom ;
   private  Boolean disponibilite ;

    public Ingredient() {
    }

    public Ingredient(String id_ingredient, String nom, Boolean disponibilite) {
        this.id_ingredient = id_ingredient;
        this.nom = nom;
        this.disponibilite = disponibilite;
    }

    public String getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(String id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
       return "{ \"id_ingredient\":" + id_ingredient + ",\" nom\": \"" + nom + "\", \"disponibilite\":\"" + disponibilite + "\"}";
    }
   
    public String toJson(){
        return "{ \"id_ingredient\":" + id_ingredient + ",\" nom\": \"" + nom + "\", \"disponibilite\":\"" + disponibilite + "\"}";
    }
   
}
