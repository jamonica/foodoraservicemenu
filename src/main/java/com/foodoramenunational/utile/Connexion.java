 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodoramenunational.utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kouka
 */
public class Connexion {
 
        public Connection getConnnection() {
         Connection connection = null;

        try {
         String connectionURL = "jdbc:mysql://localhost:3306/foodoramenunational";
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         connection = DriverManager.getConnection(connectionURL, "root", "root");


        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
         // TODO Auto-generated catch block
         e.getLocalizedMessage();
         }
         return connection;
         }

  }


        
