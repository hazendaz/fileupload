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
package com.hazendaz.ws;

import jakarta.xml.ws.Service;

import java.net.URL;

import javax.xml.namespace.QName;

public class HelloWorldClient {

    static {
        // for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

            @Override
            public boolean verify(final String hostname, final javax.net.ssl.SSLSession sslSession) {
                return hostname.equals("localhost");
            }
        });
    }

    public static void main(final String[] args) throws Exception {
        final URL url = new URL("https://localhost:8443/fileupload/hello?wsdl");
        final QName qname = new QName("http://ws.hazendaz.com/", "HelloWorldImplService");
        final Service service = Service.create(url, qname);
        final HelloWorld hello = service.getPort(HelloWorld.class);
        System.out.println(hello.getHelloWorld("mkyong"));
    }
}
