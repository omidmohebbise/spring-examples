package com.omidmohebbise.hateoas.controller;


import org.springframework.hateoas.RepresentationModel;

public class UserResource extends RepresentationModel<UserResource> {
    private long id;
    private String fullName;

    public UserResource() {
    }

    public UserResource(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
