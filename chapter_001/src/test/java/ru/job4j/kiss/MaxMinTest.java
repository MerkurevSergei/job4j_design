package ru.job4j.kiss;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxMinTest {

    static ArrayList<Integer> integers = new ArrayList<>();

    @Before
    public void setUp() {
        integers.add(5);
        integers.add(4);
        integers.add(3);
        integers.add(9);
        integers.add(7);
    }

    @Test
    public void max() {
        Integer expected = 9;
        Integer out = new MaxMin().max(integers, Comparator.comparingInt(o -> o));
        assertEquals(expected, out);
    }

    @Test
    public void min() {
        Integer expected = 3;
        Integer out = new MaxMin().min(integers, Comparator.comparingInt(o -> o));
        assertEquals(expected, out);
    }

    @Test
    public void searchSuccess() {
        Integer expected = 9;
        Integer out = new MaxMin().search(integers, Comparator.comparingInt(o -> o));
        assertEquals(expected, out);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void searchFail() {
        integers.clear();
        Integer expected = 3;
        Integer out = new MaxMin().search(integers, Comparator.comparingInt(o -> o));
        assertEquals(expected, out);
    }
}