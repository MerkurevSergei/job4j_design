package ru.job4j.set;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddSuccess() {
        SimpleSet<Integer> integers = new SimpleSet<>();
        integers.add(3);
        integers.add(2);

        Iterator<Integer> it = integers.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddDuplicate() {
        SimpleSet<Integer> integers = new SimpleSet<>();
        integers.add(3);
        integers.add(3);

        Iterator<Integer> it = integers.iterator();
        assertThat(it.next(), is(3));
        assertFalse(it.hasNext());
    }

}