/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The Class PopupBeanTest.
 */
class PopupBeanTest {

    /**
     * Duplicate should toggle flag.
     */
    @Test
    void duplicateShouldToggleFlag() {
        final PopupBean popupBean = new PopupBean();

        Assertions.assertFalse(popupBean.isShowDuplicate());
        popupBean.duplicate();
        Assertions.assertTrue(popupBean.isShowDuplicate());
        popupBean.duplicate();
        Assertions.assertFalse(popupBean.isShowDuplicate());
    }

    /**
     * Test extends should accept generic list.
     */
    @Test
    void testExtendsShouldAcceptGenericList() {
        final PopupBean popupBean = new PopupBean();

        popupBean.testExtends(List.of("test"));
        popupBean.testExtends(List.of(1, 2, 3));

        Assertions.assertFalse(popupBean.isShowDuplicate());
    }
}
