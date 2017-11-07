package com.hazendaz.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Data
@Named
@SessionScoped
public class StateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Logger            logger           = LoggerFactory.getLogger(StateBean.class);

    private String            state;
    private String            extra;
    private String            upperCased;
    private String            upperCased2;
    private String            notUpperCased;
    private double            money            = 5;

    private Cache             cache;
    private List<String>      states;

    public String check() {
        return null;
    }

    public String check2() {
        return null;
    }

    public String check3() {
        return null;
    }

    public String check6() {
        return null;
    }

    public String check7() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<String> getStates() {
        if (this.states == null || this.states.size() == 0) {
            this.states = (List<String>) this.cache.get("A").getObjectValue();
        }
        return this.states;
    }

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

    public void setStates(final ArrayList<String> value) {
        this.cache.put(new Element("A", value));
    }

}
