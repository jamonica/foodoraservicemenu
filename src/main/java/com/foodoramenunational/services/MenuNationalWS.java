/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.services;

import com.foodoramenunational.dao.IngredientDAO;
import com.foodoramenunational.dao.ItemDAO;
import com.foodoramenunational.modele.Ingredient;
import com.foodoramenunational.modele.Item;
import com.foodoramenunational.utile.Connexion;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;


/**
 * REST Web Service
 *
 * @author kouka
 */
@Path("/menu")
public class MenuNationalWS {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String identifiant;
    
    
    @POST
    @Path("/menuPost")
    @Produces(MediaType.APPLICATION_JSON )
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     public Item [] newMenu(@FormParam("identifiant") String id,@FormParam("nbitems") String nbitems, @FormParam("nomitem") String nom, @FormParam("desc") String desc,
            @FormParam("catgorie") String catgorie,@FormParam("statut") String disponibilite,@FormParam("prix") String prix,
            @FormParam("temps") String temps,@FormParam("rabais") String rabais,@FormParam("idIng") String idIng,
            @FormParam("nbIng") String nbIng,@FormParam("nomIng") String nomIng,@FormParam("statutIng") String statutIng){
             Connexion cnx= new Connexion();
             ItemDAO itemdao = new ItemDAO(cnx.getConnnection());
             IngredientDAO ingreDao = new IngredientDAO(cnx.getConnnection());
             Item [] items = new Item [Integer.parseInt(nbitems)];
             Ingredient [] ingr = new Ingredient [Integer.parseInt(nbIng)];
             for(int i=0; i<Integer.parseInt(nbitems);i++){
                    items[i]=new Item();
                    items[i].setId_item(id);
                    items[i].setNom(nom);
                    items[i].setDescription(desc);
                    items[i].setCategorie(catgorie);
                     if(disponibilite.equalsIgnoreCase("dispo"))
                        items[i].setDisponibilite(Boolean.TRUE);
                        else 
                            items[i].setDisponibilite(Boolean.FALSE);
                    for (int j=0; j<Integer.parseInt(nbIng); j++){
                        ingr[j]=new Ingredient();
                        ingr[j].setId_ingredient(idIng);
                        ingr[j].setNom(nomIng);
                        if(statutIng.equalsIgnoreCase("disp"))
                        ingr[j].setDisponibilite(Boolean.TRUE);
                        else 
                            ingr[j].setDisponibilite(Boolean.FALSE);
                        ingreDao.create(ingr[j]);
                        System.out.println("test ingredient"+ingr[j]);
                    }
                   
                    items[i].setIngredient(ingr);
                    items[i].setPrix(Double.parseDouble(prix));
                    items[i].setRabais(Double.parseDouble(rabais));
                    items[i].setTemps_preparation(Integer.parseInt(temps));
                    itemdao.create(items[i]);
             }    
            return items;   
     }
     
   
     // test du service web
    @GET
            @Path("/testing")
            @Produces(MediaType.TEXT_PLAIN)
            public Response verifyRESTService(InputStream incomingData) {
                    String result = "test du service web reussi avec succes..";

                    // return HTTP response 200 in case of success
                    return Response.status(200).entity(result).build();
            }


 }
