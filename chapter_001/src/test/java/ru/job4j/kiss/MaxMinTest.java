package ru.job4j.kiss;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxMinTest {

    static ArrayList<Integer> integers = new ArrayList<>();

    @BeforeClass
    public static void beforeClass() {
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
        Integer out = new MaxMin().max(integers, Comparator.comparingInt(o -> -1 * o));
        assertEquals(expected, out);
    }
}