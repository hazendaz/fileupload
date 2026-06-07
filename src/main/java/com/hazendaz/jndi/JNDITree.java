/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 * The Class JNDITree.
 */
public class JNDITree {
    /** The context. */
    private Context context = null;

    /** The indent level. */
    private int indentLevel = 0;

    /**
     * Instantiates a new jndi tree.
     *
     * @throws NamingException
     *             the naming exception
     */
    public JNDITree() throws NamingException {
        this.context = new InitialContext();
    }

    /**
     * Print jndi tree.
     *
     * @param ct
     *            the ct
     */
    public void printJNDITree(final String ct) {
        try {
            this.printNE(this.context.list(ct), ct);
        } catch (final NamingException e) {
            // Do nothing
        }
    }

    /**
     * Decrease indent.
     */
    private void decreaseIndent() {
        this.indentLevel -= 4;
    }

    /**
     * Increase indent.
     */
    private void increaseIndent() {
        this.indentLevel += 4;
    }

    /**
     * Print entry.
     *
     * @param next
     *            the next
     */
    private void printEntry(final NameClassPair next) {
        System.out.println(this.printIndent() + "-->" + next);
    }

    /**
     * Print indent.
     *
     * @return the string
     */
    private String printIndent() {
        final StringBuilder buf = new StringBuilder(this.indentLevel);
        for (int i = 0; i < this.indentLevel; i++) {
            buf.append(" ");
        }
        return buf.toString();
    }

    /**
     * Print ne.
     *
     * @param ne
     *            the ne
     * @param parentctx
     *            the parentctx
     */
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
