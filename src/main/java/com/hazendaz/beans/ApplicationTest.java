package com.hazendaz.beans;

import com.hazendaz.weld.AfterDeploy;
import com.hazendaz.weld.Eager;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;

@ApplicationScoped
@Eager
public class ApplicationTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private String foundAtStartup;

    @Getter
    private String svnTest;

    @PostConstruct
    public void init() {
        this.foundAtStartup = "Yes";
    }

    @AfterDeploy
    public void initAfter() {
        this.foundAtStartup = "no";
    }

}
