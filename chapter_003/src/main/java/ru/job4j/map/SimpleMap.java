package ru.job4j.map;

import ru.job4j.list.ForwardLinked;

import java.util.*;

/**
 * Simple map based on nodes array.
 *
 * @param <K> the keys
 * @param <V> the values
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public final class SimpleMap<K, V> {

    /* ---------------- Constants -------------- */
    /**
     * The default initial capacity.
     */
    private static final int INITIAL_CAPACITY = 1 << 4; // 16

    /**
     * The maximum capacity.
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    private static final float LOAD_FACTOR = 0.75f;

    /* -------------- Inner classes ------------ */

    /**
     * Bin node
     *
     * @param <K> the key
     * @param <V> the value
     */
    private final static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }

    /* ----------------- Fields ----------------- */
    /**
     * The table consist nodes
     */
    private Node<K, V>[] table;

    /**
     * Current max map capacity
     */
    private int capacity;

    /**
     * Current map size
     */
    private int size;

    /**
     * The changes counter.
     */
    private int modCount = 0;

    /* ---------------- Public operations -------------- */

    /**
     * Constructor
     */
    @SuppressWarnings("unchecked")
    public SimpleMap() {
        this.table = (Node<K, V>[]) new Node[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
    }

    /**
     * @return map size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param key   the key
     * @param value the value
     * @return true if insert success
     */
    public final boolean insert(K key, V value) {
        if (size > LOAD_FACTOR * capacity && capacity < MAXIMUM_CAPACITY) {
            resize();
        }
        Node<K, V> newNode = new Node<>(key, value);
        int i = key.hashCode() % capacity;
        if (table[i] == null) {
            table[i] = newNode;
            size++;
        } else {
            Node<K, V> cursor = table[i];
            while (cursor != null) {
                if (Objects.equals(cursor.getKey(), key)) {
                    cursor.setValue(value);
                    break;
                } else if (cursor.getNext() == null) {
                    cursor.setNext(newNode);
                    size++;
                }
                cursor = cursor.getNext();
            }
        }
        modCount++;
        return true;
    }

    /**
     * @param key the key
     * @return value from the key
     */
    public final V get(K key) {
        Node<K, V> node = getNode(key);
        return node != null ? node.getValue() : null;
    }

    /**
     * @param key the key
     * @return true if delete success
     */
    public final boolean delete(K key) {
        boolean res = false;
        int i = key.hashCode() % capacity;
        if (table[i] == null) {
            return false;
        }
        if (table[i].getKey().equals(key)) {
            table[i] = table[i].getNext();
            size--;
            modCount++;
            return true;
        }

        Node<K, V> prev = table[i];
        Node<K, V> cursor = prev.getNext();
        while (cursor != null) {
            if (cursor.getKey().equals(key)) {
                prev.setNext(cursor.getNext());
                size--;
                modCount++;
                res = true;
                break;
            }
            cursor = cursor.getNext();
        }
        return res;
    }


    /* --------------- Private operations -------------- */

    /**
     * @param key the key
     * @return Node
     */
    private Node<K, V> getNode(K key) {
        Node<K, V> res = null;
        int i = key.hashCode() % capacity;
        Node<K, V> cursor = table[i];
        while (cursor != null) {
            if (Objects.equals(cursor.getKey(), key)) {
                res = cursor;
                break;
            }
            cursor = cursor.getNext();
        }
        return res;
    }

    /**
     * resize table
     */
    private void resize() {
        Node<K, V>[] tabOld = table;
        capacity = capacity << 1;
        size = 0;
        modCount = 0;
        //noinspection unchecked
        table = (Node<K, V>[]) new Node[capacity];
        for (Node<K, V> cursor: tabOld) {
            while (cursor != null) {
                insert(cursor.getKey(), cursor.getValue());
                cursor = cursor.getNext();
            }
        }
    }

    /* ------------------- Iterators ------------------- */
    public final Iterator<V> iterator() {
        return new Iterator<>() {

            /**
             * Current node.
             */
            private Node<K, V> cursor = new Node<>(null, null);

            /**
             *
             */
            private int indexCursor;

            /**
             *
             */
            private int indexNext;

            /**
             * Next node.
             */
            private Node<K, V> next;

            /**
             * For check ConcurrentModificationException.
             */
            private final int modCountSaved = modCount;

            /**
             * @return true if has next
             */
            @Override
            public boolean hasNext() {
                indexNext = indexCursor;
                next = cursor.getNext();
                if (next != null) {
                    return true;
                }
                while (indexNext < table.length && next == null) {
                    next = table[indexNext];
                    indexNext++;
                }
                return next != null;
            }

            /**
             * @return next value
             */
            @Override
            public V next() {
                if (modCount != modCountSaved) {
                    throw new ConcurrentModificationException("Collection has been modified");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("No such");
                }
                cursor = next;
                indexCursor = indexNext;
                return next.getValue();
            }
        };
    }
}
