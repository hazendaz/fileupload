package com.hazendaz.rs;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        this.register(new WebServiceBinder());
        this.packages(true, "com.hazendaz.rs");
    }
}
