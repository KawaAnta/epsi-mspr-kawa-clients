package com.kawa.clients.clientsapi.utils;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public class NullAwareBeanUtilsBean extends BeanUtilsBean {

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if (value != null) {
            super.copyProperty(dest, name, value);
        }
    }

    public void copyProperties(Object dest, Object orig) {
        new NullAwareBeanUtilsBean().copyProperties(dest, orig);
    }
}
