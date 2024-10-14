package com.omidmohebbise.sleuth.model;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;

    public UserEO( String fullName) {
        this.fullName = fullName;
    }

    public UserEO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
