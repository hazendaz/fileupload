/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class JNDITree {
    private Context context = null;

    private int indentLevel = 0;

    public JNDITree() throws NamingException {
        this.context = new InitialContext();
    }

    public void printJNDITree(final String ct) {
        try {
            this.printNE(this.context.list(ct), ct);
        } catch (final NamingException e) {
            // Do nothing
        }
    }

    private void decreaseIndent() {
        this.indentLevel -= 4;
    }

    private void increaseIndent() {
        this.indentLevel += 4;
    }

    private void printEntry(final NameClassPair next) {
        System.out.println(this.printIndent() + "-->" + next);
    }

    private String printIndent() {
        final StringBuilder buf = new StringBuilder(this.indentLevel);
        for (int i = 0; i < this.indentLevel; i++) {
            buf.append(" ");
        }
        return buf.toString();
    }

    private void printNE(final NamingEnumeration<?> ne, final String parentctx) {
        while (ne.hasMoreElements()) {
            final NameClassPair next = (NameClassPair) ne.nextElement();
            this.printEntry(next);
            this.increaseIndent();
            this.printJNDITree(parentctx.length() == 0 ? next.getName() : parentctx + "/" + next.getName());
            this.decreaseIndent();
        }
    }
}
