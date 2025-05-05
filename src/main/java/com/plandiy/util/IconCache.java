package com.plandiy.util;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that caches icons (images) for efficient reuse across the application.
 * <p>
 * Prevents redundant loading of image resources by storing them in a memory cache.
 * Especially useful in UIs where icons are frequently reused.
 */
public class IconCache {

    private static final Map<String, Image> cache = new HashMap<>();

    /**
     * Returns the cached {@link Image} for the specified icon file name,
     * loading it from the classpath if it hasn’t been loaded before.
     *
     * @param iconFileName the name of the icon file (e.g., "icon.png")
     * @return the loaded or cached {@link Image}
     */
    public static Image get(String iconFileName) {
        return cache.computeIfAbsent(iconFileName, name -> {
            String path = "/com/plandiy/images/" + name;
            return new Image(IconCache.class.getResourceAsStream(path));
        });
    }
}
