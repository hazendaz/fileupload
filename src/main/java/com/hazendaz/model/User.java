package com.hazendaz.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import lombok.Data;

@SessionScoped
@Data
public class User implements IUser, Serializable {

    private static final long serialVersionUID = 1L;

    private String            name;

    public User() {
        // Do nothing
    }

    public User(final User another) {
        this.name = another.name; // you can access
    }

}
