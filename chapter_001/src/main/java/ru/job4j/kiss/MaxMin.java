package ru.job4j.kiss;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class find max and min elements
 */
public class MaxMin {
    /**
     * find max element
     * @param value - List values
     * @param comparator - Comparator implements
     * @param <T> - type list elements
     * @return - max element from list
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return Collections.max(value, comparator);
    }

    /**
     * find min element
     * @param value - List values
     * @param comparator - Comparator implements
     * @param <T> - type list elements
     * @return - max element from list
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return Collections.min(value, comparator);
    }
}