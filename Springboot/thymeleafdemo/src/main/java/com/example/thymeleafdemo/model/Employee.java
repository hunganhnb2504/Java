package com.example.thymeleafdemo.model;

public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private String email;

    public Employee(){

    }
    public Employee(int id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + "]";
    }
}
