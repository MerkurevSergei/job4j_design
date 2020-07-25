package ru.job4j.cache;

import java.io.IOException;

/**
 * The {@code Cache} interface for cache implementation.
 *
 * @param <K> key.
 * @param <V> value.
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public interface Cache<K, V> {

    /**
     * Get the value from cache.
     *
     * @param key for get value.
     * @return value.
     * @throws Exception if error occurs.
     */
    V get(K key) throws Exception;

    /**
     * Put the value to cache.
     *
     * @param key   for put to cache.
     * @param value for put to cache.
     */
    void put(K key, V value);
}
