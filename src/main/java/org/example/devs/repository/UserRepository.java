package org.example.devs.repository;

import org.example.devs.model.User;
import org.example.devs.util.DatabaseConnection;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IRepository<User>{

    // esto para asegurarnos que se cumpla el principio Atomicity una sola transación
    private Connection getConnection() throws SQLException{
            return DatabaseConnection.getConnection();
    };



    @Override
    public List<User> findAll() throws SQLException {
        List<User> listUsers = new ArrayList<>();
        try(Connection myConn = DatabaseConnection.getConnection();
            Statement myStam = myConn.createStatement();
            ResultSet myRest = myStam.executeQuery("SELECT * FROM users")){
            while(myRest.next()){
                User u = createUser(myRest);
                listUsers.add(u);
            }

        }
        return listUsers;
    }

    @Override
    public User getByID(Integer id) throws SQLException {
        User users = null;
        try(Connection myConn = DatabaseConnection.getConnection();
            PreparedStatement myPrepStam = myConn.prepareStatement("SELECT * FROM users WHERE id = ?")){
            myPrepStam.setInt(1,id);
            try(ResultSet myRes = myPrepStam.executeQuery()){
                if(myRes.next()){
                    users = createUser(myRes);
                }

            }
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql;
        if(user.getId() > 0){
            sql = "UPDATE users set document = ?, name = ?, lastName = ?, email = ?, password = ?, idRoles = ?, curp = ? WHERE id = ?";
        }else{
            sql = "INSERT INTO users (document, name, lastName, email, password, idRoles, curp) values (?,?,?,?,?,?,?)";
        }
        try(Connection myConn =  DatabaseConnection.getConnection();
            PreparedStatement myPrepStatm = myConn.prepareStatement(sql)){
            myPrepStatm.setString(1,user.getDocument());
            myPrepStatm.setString(2,user.getName());
            myPrepStatm.setString(3, user.getLastName());
            myPrepStatm.setString(4, user.getEmail());
            myPrepStatm.setString(5, user.getPassword());
            myPrepStatm.setInt(6, user.getIdRol());
            myPrepStatm.setString(7, user.getCurp());

            if (user.getId() != 0){
                myPrepStatm.setInt(8,user.getId());
            }
            myPrepStatm.executeUpdate();
        }

    }

    @Override
    public void delete(Integer id)  throws SQLException{
        try(Connection myConn = DatabaseConnection.getConnection();
            PreparedStatement myPrepStatm = myConn.prepareStatement("DELETE FROM users WHERE id = ?")){
            myPrepStatm.setInt(1, id);
            myPrepStatm.executeUpdate();

        }
    }
    public User createUser (ResultSet rest) throws SQLException {
        User u = new User();
        u.setId(rest.getInt("id"));
        u.setDocument(rest.getString("document"));
        u.setName(rest.getString("name"));
        u.setLastName(rest.getString("lastName"));
        u.setEmail(rest.getString("email"));
        u.setPassword(rest.getString("password"));
        u.setIdRol(rest.getInt("idRoles"));
        u.setCurp(rest.getString("curp"));
        return u;
    }

}
