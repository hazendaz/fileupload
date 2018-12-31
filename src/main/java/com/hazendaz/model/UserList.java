package com.hazendaz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import lombok.Data;

@ApplicationScoped
@Data
public class UserList implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<User> userList;

    public UserList() {
        this.userList = new ArrayList<>();
    }
}
