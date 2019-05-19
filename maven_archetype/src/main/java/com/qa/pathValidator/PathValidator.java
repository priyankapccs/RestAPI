package com.qa.pathValidator;

import java.util.HashMap;
import java.util.Map;

public class PathValidator {
    private static Map<String, String> attributeMap = new HashMap<>();

    static {

        attributeMap.put("id", "id");
        attributeMap.put("name", "name");
    }

    public static String getAttribute(Object key) {
        return attributeMap.get(key);
    }

}
