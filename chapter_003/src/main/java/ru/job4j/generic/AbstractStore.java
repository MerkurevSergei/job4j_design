package ru.job4j.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Abstract store implements Store interface.
 * Use SimpleArray as store, for this reason convert String id to Integer
 *
 * @param <T> - type of stored elements extends of Base
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    /**
     * store
     */
    final private SimpleArray<T> store;

    /**
     * @param size -max size of store
     */
    public AbstractStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    /**
     * @param model - item to be stored
     */
    @Override
    public void add(T model) throws IllegalArgumentException {
        if (model == null) {
            throw new IllegalArgumentException("Null is illegal item to be stored");
        }
        store.add(model);
    }

    /**
     * @param id - id element to be replaced
     * @param model - replacement element
     * @return result - true if success
     */
    @Override
    public boolean replace(String id, T model) {
        boolean res = false;
        if (model != null) {
            final int i = store.search((o) -> o.getId().equals(id));
            if (i != -1) {
                store.set(i, model);
                res = true;
            }
        }
        return res;
    }

    /**
     * @param id - the id element to be deleted
     * @return result - true if success
     */
    @Override
    public boolean delete(String id) {
        boolean res = false;
        final int i = store.search((o) -> o.getId().equals(id));
        if (i != -1) {
            store.remove(i);
            res = true;
        }
        return res;
    }

    /**
     * @param id - id element from search
     * @return - the element
     */
    @Override
    public T findById(String id) {
        T res = null;
        final Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (id.equals(element.getId())) {
                res = element;
                break;
            }
        }
        return res;
    }

    /**
     * @return Iterator
     */
    protected Iterator<T> iterator() {
        return store.iterator();
    }
}
