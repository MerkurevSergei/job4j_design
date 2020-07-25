package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code FileCache} implementation from {@code Cache} interface.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class FileCache implements Cache<Path, String> {
    private final SoftReference<Map<Path, String>> softCache = new SoftReference<>(new HashMap<>());

    /**
     * Get the value from cache.
     *
     * @param key for get value.
     * @return value.
     * @throws  IOException if an I/O error occurs reading from the file.
     */
    @Override
    public String get(Path key) throws IOException {
        Map<Path, String> cache = softCache.get();
        cache = (cache == null) ? new HashMap<>() : cache;
        String result = cache.get(key);
        if (result == null) {
            result = Files.readString(key, Charset.defaultCharset());
            put(key, result);
        }
        return result;
    }

    /**
     * Put the value to cache.
     *
     * @param key   for put to cache.
     * @param value for put to cache.
     */
    @Override
    public void put(Path key, String value) {
        Map<Path, String> cache = softCache.get();
        cache = (cache == null) ? new HashMap<>() : cache;
        cache.put(key, value);
    }
}
