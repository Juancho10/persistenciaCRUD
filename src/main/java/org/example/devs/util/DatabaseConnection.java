package org.example.devs.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/ecommercesanyo";
    private static final String user = "root";
    private static final String pass = "";

    //private static Connection myConn;

    //creacion del pool de conexiones
    private static BasicDataSource pool;
    /*con el metodo getInstance() -> esta forma de crear esta conexión
    * lo que hace es que se implemente el patron singleton que asegura
    * que solo se tenga una instancia de la clase.
    * Esto para evitar varias conexión a la base de datos.
    * */
    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null){
            //myConn = DriverManager.getConnection(url,user,pass);
            //se instancia la clase
            pool = new BasicDataSource();
            //le pasamos los datos basicos a la conexión
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(pass);
            // se indica el tamaño inicial del pool
            pool.setInitialSize(3);
            // se indica el numero total de conexiones
            pool.setMaxTotal(10);
            // se indica el numero maximo de conexiones inactivas
            pool.setMaxIdle(10);
            // se indica el numero minimo de conexiones inactivas
            pool.setMinIdle(3);

        }
        return pool;
    }
    // metodo para hacer una sola conexión al pool
    public static Connection getConnection() throws SQLException{
        return getInstance().getConnection();
    }
}
