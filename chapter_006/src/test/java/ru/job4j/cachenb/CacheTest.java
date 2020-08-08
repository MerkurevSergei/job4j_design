package ru.job4j.cachenb;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void add() {
        final Cache cache = new Cache();
        cache.add(new Base(1, 1));
        cache.add(new Base(1, 3));
        assertEquals(1, cache.get(1).getVersion());
    }

    @Test
    public void update() {
        final Cache cache = new Cache();
        cache.add(new Base(1, 1));
        cache.update(new Base(1, 2));
        cache.update(new Base(1, 3));
        assertEquals(3, cache.get(1).getVersion());
        assertFalse(cache.withException());
    }

    @Test
    public void whenUpdateException() {
        final Cache cache = new Cache();
        cache.add(new Base(1, 1));
        cache.update(new Base(1, 3));
        assertEquals(1, cache.get(1).getVersion());
        assertTrue(cache.withException());
    }

    @Test
    public void delete() {
        final Cache cache = new Cache();
        final Base base = new Base(1, 1);
        cache.add(base);
        cache.delete(base);
        assertNull(cache.get(1));
    }
}