package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {

    @Test
    public void whenInsertAndGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.insert("A", 1));
        assertThat(map.get("A"), is(1));
    }

    @Test
    public void whenInsertSameKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("A", 1);
        map.insert("A", 2);
        assertThat(map.get("A"), is(2));
        assertThat(map.getSize(), is(1));
    }

    @Test
    public void whenManyInsert() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        for (int i = 0; i < 20; i++) {
            map.insert(String.valueOf(i), 1);
        }
        assertThat(map.getSize(), is(20));
    }

    @Test
    public void whenDelete() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("A", 1);
        assertTrue(map.delete("A"));
        assertNull(map.get("A"));
        assertFalse(map.delete("A"));
    }

    @Test
    public void whenIteratorNextNext() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("A", 1);
        map.insert("B", 2);
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorExceptionNext() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("A", 1);
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        fail();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorExceptionNextAfterInsert() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("A", 1);
        Iterator<Integer> it = map.iterator();
        map.insert("B", 2);
        it.next();
        fail();
    }
}