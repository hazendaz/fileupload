/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class StateBean.
 */
@Data
@Named
@SessionScoped
public class StateBean implements Serializable {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /** The logger. */
    private Logger logger = LoggerFactory.getLogger(StateBean.class);

    /** The state. */
    private String state;
    /** The extra. */
    private String extra;
    /** The upper cased. */
    private String upperCased;
    /** The upper cased2. */
    private String upperCased2;
    /** The not upper cased. */
    private String notUpperCased;
    /** The money. */
    private double money = 5;

    /** The cache. */
    private Cache cache;
    /** The states. */
    private List<String> states;

    /**
     * Check.
     *
     * @return the string
     */
    public String check() {
        return null;
    }

    /**
     * Check2.
     *
     * @return the string
     */
    public String check2() {
        return null;
    }

    /**
     * Check3.
     *
     * @return the string
     */
    public String check3() {
        return null;
    }

    /**
     * Check6.
     *
     * @return the string
     */
    public String check6() {
        return null;
    }

    /**
     * Check7.
     *
     * @return the string
     */
    public String check7() {
        return null;
    }

    /**
     * Gets the states.
     *
     * @return the states
     */
    @SuppressWarnings("unchecked")
    public List<String> getStates() {
        if (this.states == null || this.states.size() == 0) {
            this.states = (List<String>) this.cache.get("A").getObjectValue();
        }
        return this.states;
    }

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        this.cache = CacheManager.getInstance().getCache("smallCache");
        this.states = new ArrayList<>();
        this.states.add("Ohio");
        this.states.add("Florida");
        this.cache.put(new Element("A", this.states));

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Test Message from @PostConstruct", null));

    }

    /**
     * Sets the states.
     *
     * @param value
     *            the value
     */
    public void setStates(final ArrayList<String> value) {
        this.cache.put(new Element("A", value));
    }

}
