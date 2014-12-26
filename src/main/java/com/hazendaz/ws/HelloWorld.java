package com.hazendaz.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

// Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface HelloWorld {

    @WebMethod(operationName = "getHelloWorld")
    String getHelloWorld(final String name);

}