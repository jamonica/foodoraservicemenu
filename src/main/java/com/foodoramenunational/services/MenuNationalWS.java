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
    
    
    @GET
    @Path("/menupost")
    @Produces( MediaType.APPLICATION_JSON)
     public  List <Item>  newMenu(){
             Connexion cnx= new Connexion();
             ItemDAO itemdao = new ItemDAO(cnx.getConnnection());
             List items = new LinkedList<>();
             items=itemdao.findAll();
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
