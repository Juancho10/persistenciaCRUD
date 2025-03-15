package org.example.devs.repository;

public class UserRepository {
    private int id;
    private String document;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int idRol;

    public UserRepository(){}

    public UserRepository(String document, String name, String lastName, String email, String password, int idRol) {
        this.document = document;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}
