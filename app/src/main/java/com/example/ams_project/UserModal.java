package com.example.ams_project;

public class UserModal {

    private int id;
    private String name;
    private String email;
    private Object picture;


    public UserModal(int id, String name, String email, Object picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public UserModal() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }
}
