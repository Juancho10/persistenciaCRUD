package org.example.devs;

import org.example.devs.model.User;
import org.example.devs.repository.IRepository;
import org.example.devs.repository.UserRepository;
import org.example.devs.util.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args){

        try (Connection myConn = DatabaseConnection.getInstance()){;
            IRepository<User> repository = new UserRepository();
            User users = new User();
            System.out.println("------------ listando usuarios--------------------");
            repository.findAll().forEach(System.out::println);
            System.out.println("-------------actualizando usuario -------------------------");
            users.setId(4);
            users.setDocument("12345678");
            users.setName("Gabriela");
            users.setLastName("Lizarazo Solorzano");
            users.setEmail("gabriela@gmail.com");
            users.setPassword("Gabriela1234");
            users.setIdRol(1);
            repository.save(users);

            System.out.println("---------- usuarios actualizados -------------------");
            repository.findAll().forEach(System.out::println);
            //System.out.println(repository.getByID(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}