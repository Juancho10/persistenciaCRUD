package org.example.devs.model;

public class User {
    private int id;
    private String document;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int idRol;
    private String curp;



    public User(){}

    public User(String document, String name, String lastName, String email, String password, int idRol, String curp) {
        this.document = document;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
        this.curp = curp;
    }
    public User(int id, String document, String name, String lastName, String email, String password, int idRol){
        this.id = id;
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
    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Override
    public String toString() {
        return "ID | NOMBRE | APELLIDO | DOCUMENTO | CORREO\n"+
                id + " | "+ name+" | "+lastName+" | "+" | "+document+" | "+email;
    }
}
