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
     * current index
     */
    private int i;

    /**
     * next index
     */
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
        nextIndex = i + 1;
        while (array[nextIndex] % 2 != 0) {
            nextIndex++;
            if (nextIndex == array.length) {
                break;
            }
        }
        return nextIndex < array.length;
    }

    /**
     * @return next element or throw NoSuchElementException
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such");
        }
        i = nextIndex;
        return array[i];
    }
}
