package com.plandiy.util;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class IconCache {

    private static final Map<String, Image> cache = new HashMap<>();

    public static Image get(String iconFileName) {
        return cache.computeIfAbsent(iconFileName, name -> {
            String path = "/com/plandiy/images/" + name;
            return new Image(IconCache.class.getResourceAsStream(path));
        });
    }
}
