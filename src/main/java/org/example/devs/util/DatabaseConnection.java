package org.example.devs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/ecommercesanyo";
    private static final String user = "root";
    private static final String pass = "";

    private static Connection myConn;

    /*con el metodo getInstance() -> esta forma de crear esta conexión
    * lo que hace es que se implemente el patron singleton que asegura
    * que solo se tenga una instancia de la clase.
    * Esto para evitar varias conexión a la base de datos.
    * */
    public static Connection getInstance() throws SQLException {
        if (myConn == null){
            myConn = DriverManager.getConnection(url,user,pass);
        }
        return myConn;
    }
}
