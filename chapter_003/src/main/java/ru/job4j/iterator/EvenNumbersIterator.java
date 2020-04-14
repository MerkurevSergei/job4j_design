package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator on even number from array.
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    /**
     * iterable array
     */
    final private int[] array;

    /**
     * next index
     */
    private int i;

    private int nextIndex;

    /**
     * @param array init iterable array
     */
    public EvenNumbersIterator(int[] array) {
        this.array = array;
        i = -1;
    }

    /**
     * @return true if has next element from array
     */
    @Override
    public boolean hasNext() {
        return nextIndex() < array.length;
    }

    /**
     * @return next element or throw NoSuchElementException
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such");
        }
        i = nextIndex();
        return array[i];
    }

    /**
     * @return get index of the next even element
     */
    public int nextIndex() {
        int j = i + 1;
        while (array[j] % 2 != 0) {
            j++;
            if (j == array.length) {
                break;
            }
        }
        return j;
    }
}
