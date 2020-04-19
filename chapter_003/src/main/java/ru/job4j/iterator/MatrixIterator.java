package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator from jagged array
 */
public class MatrixIterator implements Iterator<Integer> {
    /**
     * store of the matrix
     */
    private final int[][] array;
    /**
     * current column
     */
    private int i;
    /**
     * current row
     */
    private int j;
    /**
     * next column
     */
    private int nextI;
    /**
     * next row
     */
    private int nextJ;


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
        boolean res = true;
        nextI = i;
        nextJ = j;
        nextI++;
        while (nextI >= array[nextJ].length) {
            nextI = 0;
            nextJ++;
            if (nextJ >= array.length) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * @return next value
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such");
        }
        i = nextI; j = nextJ;
        return array[j][i];
    }
}
