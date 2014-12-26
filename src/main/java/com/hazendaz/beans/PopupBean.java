package com.hazendaz.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Data;

@Data
@Named
@SessionScoped
public class PopupBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean           showDuplicate;

    public void duplicate() {
        if (this.showDuplicate) {
            this.showDuplicate = false;
        } else {
            this.showDuplicate = true;
        }
    }

    public boolean isShowDuplicate() {
        return this.showDuplicate;
    }

    /**
     * @param <T>
     *            Generic type for test string
     * @param testString
     *            Generic list test (not used)
     */
    public <T> void testExtends(final List<T> testString) {
        // TODO Test of generics
    }

}
