package ru.job4j.list;

import java.util.*;

/**
 * Resizable-array with iterator.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {

    /**
     * The array stored the elements.
     */
    private Object[] container;

    /**
     * Size of the SimpleArray, the number of elements it contains.
     */
    private int size = 0;

    /**
     * The changes counter.
     */
    protected int modCount = 0;

    /**
     * SimpleArray standard constructor with default start capacity.
     */
    public SimpleArray() {
        this.container = new Object[10];
    }

    /**
     * SimpleArray standard constructor with user start capacity.
     */
    public SimpleArray(int capacity) {
        this.container = new Object[capacity];
    }

    /**
     * Return element at the index.
     *
     * @param index - index of element
     * @return - element at the index position
     * @throws IndexOutOfBoundsException if index not valid
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        try {
            Objects.checkIndex(index, size);
        } catch (IndexOutOfBoundsException ex) {
            final NoSuchElementException e = new NoSuchElementException();
            e.addSuppressed(ex);
            throw e;
        }
        return (T) container[index];
    }

    /**
     * Add item to SimpleArray
     *
     * @param model - new item
     * @throws NegativeArraySizeException if {@code newCapacity} is negative
     */
    public void add(T model) {
        modCount++;
        if (size == container.length) {
            int newCapacity = (int) (container.length * 1.5 + 1);
            container = Arrays.copyOf(container, newCapacity);
        }
        container[size++] = model;
    }

    /**
     * @return SimpleArray size
     */
    public int size() {
        return size;
    }

    /**
     * @return iterator implementation
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator();
    }

    /**
     * Simple Array Iterator
     */
    private class SimpleArrayIterator implements Iterator<T> {

        /**
         * current index
         */
        private int cursor;

        private final int modCountSaved;

        /**
         * constructor
         */
        public SimpleArrayIterator() {
            this.cursor = 0;
            this.modCountSaved = modCount;
        }

        /**
         * @return true if has next element
         */
        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * @return next element or throw
         */
        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No such");
            }
            if (modCount != modCountSaved) {
                throw new ConcurrentModificationException("Collection has been modified");
            }
            return (T) container[cursor++];
        }
    }
}
