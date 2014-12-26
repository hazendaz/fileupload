package com.hazendaz.ws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWorldClient {

    static {
        // for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

            @Override
            public boolean verify(final String hostname, final javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("localhost")) {
                    return true;
                }
                return false;
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