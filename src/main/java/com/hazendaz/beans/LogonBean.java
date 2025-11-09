/*
 * fileupload (https://github.com/hazendaz/fileupload)
 *
 * Copyright 2009-2025 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
package com.hazendaz.beans;

import com.hazendaz.jndi.JNDITree;
import com.hazendaz.model.User;
import com.hazendaz.model.UserList;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Any;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

import javax.naming.NamingException;

// import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.ToString;

import org.slf4j.Logger;

@Data
@ToString(exclude = "context")
@Named
@RequestScoped
public class LogonBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger logger;

    @Inject
    private FacesContext context;

    @Inject
    @Any
    private ApplicationTest applicationTest;

    @Inject
    private User user;

    // TODO In order to test annotation processing with hibernate validator, use
    // this
    // @Past
    // private String cannotBePast;

    private String userName = null;

    private String sad;

    private String thisIsNotFast;

    @Inject
    private UserList userList;

    public String clear() {
        this.logger.info("testme");
        this.setUserName(null);
        return "logon";
    }

    public void contextTest() {
        final HttpSession session = (HttpSession) this.context.getExternalContext().getSession(false);
        session.getMaxInactiveInterval();
    }

    public String happy(final String value) {
        return value;
    }

    @PostConstruct
    public void init() {
        this.sad = "Had Expression Language Not Worked, I'd be sad";
        this.userName = this.user.getName();
        this.thisIsNotFast = this.applicationTest.getFoundAtStartup();
    }

    public String logon() {
        // Attempt to print out jndi tree
        try {
            new JNDITree().printJNDITree("java:comp/env");
        } catch (final NamingException e) {
            this.logger.error("{}", e);
        }
        this.user.setName(this.getUserName());
        final User myUser = new User();
        myUser.setName(this.user.getName());
        this.userList.getUserList().add(myUser);
        this.contextTest();
        return "fileUpload";
    }

}
