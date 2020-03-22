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
     *
     * @param value      - List values
     * @param comparator - Comparator implements
     * @param <T>        - type list elements
     * @return - max element from list
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return search(value, comparator);
    }

    /**
     * find min element
     *
     * @param value      - List values
     * @param comparator - Comparator implements
     * @param <T>        - type list elements
     * @return - max element from list
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return search(value, comparator);
    }

    /**
     * travers list elements and search use comparator
     *
     * @param values     - list of values
     * @param comparator - compare function
     * @param <T>        - generics
     * @return - result of search from compare
     */
    public <T> T search(List<T> values, Comparator<T> comparator) {
        T res = values.get(0);
        for (T value : values) {
            res = comparator.compare(res, value) > 0 ? res : value;
        }
        return res;
    }
}