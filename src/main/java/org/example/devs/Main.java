package org.example.devs;

import org.example.devs.model.User;
import org.example.devs.repository.IRepository;
import org.example.devs.repository.UserRepository;
import org.example.devs.util.DatabaseConnection;
//import org.example.devs.view.SwingApp;

import java.sql.*;
import java.util.List;

import static org.example.devs.util.DatabaseConnection.getInstance;

public class Main {
    public static void main(String[] args) throws SQLException {
        IRepository<User> repository = new UserRepository();

        System.out.println("------------ listando usuarios--------------------");
        repository.findAll().forEach(System.out::println);
        /* implementando una unidad atomica

        try(Connection myConn = getInstance()) {
            if (myConn.getAutoCommit()){
                myConn.setAutoCommit(false);
            }
            try{
                IRepository<User> repository = new UserRepository(myConn);
                User user = new User();
                System.out.println("--------- creando usuario ---------");
                user.setDocument("12345678");
                user.setName("Gabriela");
                user.setLastName("Lizarazo Solorzano");
                user.setEmail("gabriela@gmail.com");
                user.setPassword("Gabriela1234");
                user.setIdRol(1);
                user.setCurp("1s5f2s5fgsFGESGVSG");

                repository.save(user);
                user.setDocument("1234");
                user.setName("Pepito");
                user.setLastName("Perez");
                user.setEmail("pepito@gmail.com");
                user.setPassword("Pepito1234");
                user.setIdRol(1);
                user.setCurp("1s5f2s5fgsFGESGVSG");
                // realiza el cambio si es exitoso cada una de las operaciones
                myConn.commit();
            } catch (Exception e) {
                //si arroja alguna excepción vuelve al estado donde esta correcto sin realizar cambios
                myConn.rollback();
                throw new RuntimeException(e);
            }
        }
        /*SwingApp sw = new SwingApp();
        sw.setVisible(true);
         */
        /*
        try (Connection myConn = DatabaseConnection.getInstance()){;
            IRepository<User> repository = new UserRepository();
            User users = new User();
            System.out.println("------------ listando usuarios--------------------");
            repository.findAll().forEach(System.out::println);
            /*System.out.println("-------------actualizando usuario -------------------------");
            users.setId(4);
            users.setDocument("12345678");
            users.setName("Gabriela");
            users.setLastName("Lizarazo Solorzano");
            users.setEmail("gabriela@gmail.com");
            users.setPassword("Gabriela1234");
            users.setIdRol(1);
            repository.save(users);
            */
        /*
            System.out.println("---------- Eliminado usuario -----------------------");
            repository.delete(4);
            System.out.println("---------- usuarios actualizados -------------------");
            repository.findAll().forEach(System.out::println);
            //System.out.println(repository.getByID(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */
    }
}