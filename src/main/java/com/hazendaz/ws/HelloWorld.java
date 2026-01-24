/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.Use;

// Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface HelloWorld {

    @WebMethod(operationName = "getHelloWorld")
    String getHelloWorld(final String name);

}
