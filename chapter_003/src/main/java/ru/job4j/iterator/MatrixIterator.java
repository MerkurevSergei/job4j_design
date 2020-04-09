package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator from jagged array
 */
public class MatrixIterator implements Iterator<Integer> {
    final int[][] array;
    int i;
    int j;

    /**
     * @param array - iterable array
     */
    public MatrixIterator(int[][] array) {
        this.array = array;
        this.i = -1;
        this.j = 0;
    }

    /**
     * @return true if has next
     */
    @Override
    public boolean hasNext() {
        return j < array.length - 1;
    }

    /**
     * @return next value
     */
    @Override
    public Integer next() {
        i++;
        if (i == array[j].length) {
            i = 0;
            j++;
        }
        if (j > array.length) {
            throw new NoSuchElementException("No such");
        }
        return array[j][i];
    }
}
