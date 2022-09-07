package com.acme.core.shared.utils;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

@UtilityClass
public class I18nUtils {

    public static String getMessage(String property) {
        return ResourceBundle.getBundle("messages").getString(property);
    }
}
