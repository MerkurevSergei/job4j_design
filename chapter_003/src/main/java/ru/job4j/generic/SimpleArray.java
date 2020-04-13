package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple wrapper over generic array.
 *
 * @param <E> - array type
 */
public class SimpleArray<E> {
    /**
     * generic array
     */
    private final Object[] array;

    /**
     * current index
     */
    private int position;

    /**
     * @param maxSize - max size array
     */
    public SimpleArray(int maxSize) {
        this.array = new Object[maxSize];
        this.position = -1;
    }

    /**
     * add element to array
     *
     * @param model - adding element or throw exception
     */
    public void add(E model) {
        if (size() >= maxSize()) {
            throw new ArrayIndexOutOfBoundsException("overflow");
        }
        array[++position] = model;
    }

    /**
     * replace element in array or throw exception
     *
     * @param index - index of replacement element
     * @param model - replacement element
     */
    public void set(int index, E model) {
        if (index > size() - 1) {
            throw new ArrayIndexOutOfBoundsException("invalid index");
        }
        array[index] = model;
    }

    /**
     * remove element from array
     *
     * @param index - remove index element
     */
    public void remove(int index) {
        if (index > size() - 1) {
            throw new ArrayIndexOutOfBoundsException("invalid index");
        }
        if (size() - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size() - 1 - index);
        }
        position--;
    }

    /**
     * @param index - index
     * @return - element from array
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index > size() - 1) {
            throw new ArrayIndexOutOfBoundsException("invalid index");
        }
        return (E) array[index];
    }

    /**
     * @return size of array
     */
    public int size() {
        return position + 1;
    }

    /**
     * @return max available size
     */
    private int maxSize() {
        return array.length;
    }

    /**
     * @return SimpleArrayIterator
     */
    public Iterator<E> iterator() {
        return new SimpleArrayIterator();

    }

    /**
     * Simple Array Iterator
     */
    private class SimpleArrayIterator implements Iterator<E> {

        /**
         * current index
         */
        int index;

        /**
         * constructor
         */
        public SimpleArrayIterator() {
            this.index = 0;
        }

        /**
         * @return true if has next element
         */
        @Override
        public boolean hasNext() {
            return index < size();
        }

        /**
         * @return next element or throw
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No such");
            }
            return (E) array[index++];
        }
    }

}
