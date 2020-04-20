package ru.job4j.set;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

/**
 * SimpleSet based on SimpleArray.
 *
 * @param <E> - generic type
 */
public final class SimpleSet<E> implements Iterable<E> {

    /**
     * Store for set.
     */
    private final SimpleArray<E> store = new SimpleArray<>(100);

    /**
     * Add item to store.
     *
     * @param item -item to be stored
     */
    public final void add(E item) {
        if (store.indexOf(item) == -1) {
            store.add(item);
        }
    }

    /**
     * @return Iterator.
     */
    @Override
    public final Iterator<E> iterator() {
        return store.iterator();
    }
}
