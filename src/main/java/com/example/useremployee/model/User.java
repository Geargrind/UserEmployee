package com.example.useremployee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(unique = true)
    private String email;
    //@JsonIgnore
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference //do not include in json response
    private Employee employee;

    public User(){}
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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


}
