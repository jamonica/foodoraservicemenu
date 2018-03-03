/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.services;

import com.foodoramenunational.dao.ItemDAO;
import com.foodoramenunational.modele.Item;
import com.foodoramenunational.utile.Connexion;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
 
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 

@Path("/")
public class AjouterMenuService {
	@POST
	@Path("/ajoutermenuservice")
 @Produces(MediaType.APPLICATION_JSON)
 public String getmenu() {
 Connexion cnx= new Connexion();
  List<Item> items = new LinkedList<>();

ItemDAO itemDao = new ItemDAO(cnx.getConnnection());

items = itemDao.findAll();
  String menu="[";
            if (items!=null){
                for (int i=0;i<(items.size()-2);i++){
                        menu +="\""+items.get(i).getId_item()+"\",";
                        menu +="\""+items.get(i).getNom()+"\",";
                        menu +="\""+items.get(i).getDescription()+"\",";
                        menu +="\""+items.get(i).getCategorie()+"\",";
                        menu +="\""+items.get(i).getIngredient()+"\",";
                        menu +="\""+items.get(i).getDisponibilite()+"\",";
                        menu +="\""+items.get(i).getPrix()+"\",";
                        menu +="\""+items.get(i).getRabais()+"\",";
                        menu +="\""+items.get(i).getTemps_preparation()+"\",";
            }
            menu +="\""+items.get(items.size()-1).getId_item()+"\",";
            menu +="\""+items.get(items.size()-1).getNom()+"\",";
            menu +="\""+items.get(items.size()-1).getDescription()+"\",";
            menu +="\""+items.get(items.size()-1).getCategorie()+"\",";
            menu +="\""+items.get(items.size()-1).getIngredient()+"\",";
            menu +="\""+items.get(items.size()-1).getDisponibilite()+"\",";
            menu +="\""+items.get(items.size()-1).getPrix()+"\",";
            menu +="\""+items.get(items.size()-1).getRabais()+"\",";
            menu +="\""+items.get(items.size()-1).getTemps_preparation()+"\",";

            }
            menu+="]";
            System.out.println(menu);
        return menu;   
 }

 @GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "AjouterMenuService demarré avec succès..";
 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}
}
