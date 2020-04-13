package ru.job4j.generic;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class SimpleArrayTest {

    @Test
    public void whenAddSuccess() {
        final SimpleArray<Integer> integers = new SimpleArray<>(2);
        integers.add(1);
        assertTrue(integers.get(0) == 1 && integers.size() == 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddThrowIndexOutOfBounds() {
        final SimpleArray<Integer> integers = new SimpleArray<>(0);
        integers.add(1);
        assertThat(integers.get(0), is(1));
    }

    @Test
    public void whenSetSuccess() {
        final SimpleArray<Integer> integers = new SimpleArray<>(5);
        integers.add(1);
        integers.set(0, 2);
        assertThat(integers.get(0), is(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetThrowIndexOutOfBounds() {
        final SimpleArray<Integer> integers = new SimpleArray<>(5);
        integers.add(1);
        integers.set(2, 2);
        assertThat(integers.get(0), is(2));
    }

    @Test
    public void whenRemoveSuccess() {
        final SimpleArray<Integer> integers = new SimpleArray<>(5);
        integers.add(0);
        integers.add(1);
        integers.remove(0);
        assertTrue(integers.get(0) == 1 && integers.size() == 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenRemoveThrowIndexOutOfBounds() {
        final SimpleArray<Integer> integers = new SimpleArray<>(5);
        integers.add(0);
        integers.remove(1);
        assertThat(integers.get(0), is(2));
    }

    @Test
    public void whenGetSuccess() {
        final SimpleArray<Integer> integers = new SimpleArray<>(5);
        integers.add(0);
        integers.add(1);
        integers.add(2);
        assertEquals(1, (int) integers.get(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetThrowIndexOutOfBounds() {
        final SimpleArray<Integer> integers = new SimpleArray<>(5);
        integers.add(0);
        assertThat(integers.get(1), is(2));
    }

    @Test
    public void whenIteratorNotNull() {
        final SimpleArray<Integer> integers = new SimpleArray<>(2);
        integers.add(1);
        integers.add(5);
        assertNotNull(integers.iterator());
    }

    @Test
    public void whenIteratorHasNextNextSuccess() {
        final SimpleArray<Integer> integers = new SimpleArray<>(2);
        integers.add(1);
        integers.add(5);

        final Iterator<Integer> it = integers.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextThrowException() {
        final SimpleArray<Integer> integers = new SimpleArray<>(1);
        integers.add(1);

        final Iterator<Integer> it = integers.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(5));
    }
}