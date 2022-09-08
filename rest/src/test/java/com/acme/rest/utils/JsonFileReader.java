package com.acme.rest.utils;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonFileReader {
    public static String getJson(String filePath) {
        try {
            return Resources.toString(Resources.getResource(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
