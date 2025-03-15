package org.example.devs;

import org.example.devs.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        try (Connection myConn = DatabaseConnection.getInstance();
             Statement myStam = myConn.createStatement();
             ResultSet myRest = myStam.executeQuery("SELECT * FROM users");){

                System.out.println("estas dentro");

                while(myRest.next()){
                    System.out.println(myRest.getString("name"));
                }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("algo salio mal");
        }
    }
}