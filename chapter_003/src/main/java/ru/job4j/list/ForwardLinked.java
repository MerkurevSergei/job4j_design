package ru.job4j.list;

import java.util.*;

/**
 * Forward Linked store
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public final class ForwardLinked<T> implements Iterable<T> {
    /**
     * top item
     */
    private Node<T> head;

    /**
     * tail item
     */
    private Node<T> tail;

    /**
     * The changes counter.
     */
    protected int modCount = 0;

    /**
     * @param value - value to be stored
     */
    public final void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        modCount++;
    }

    /**
     * deleted first element
     *
     * @return deleted item
     * @throws NoSuchElementException exception
     */
    public final T deleteFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("No such");
        }
        Node<T> deleted = head;
        head = head.next;
        modCount++;
        return deleted.value;
    }

    /**
     * @return iterator
     */
    @Override
    public final Iterator<T> iterator() {
        return new Iterator<>() {
            /**
             * set iterable node to head
             */
            Node<T> node = head;

            private final int modCountSaved = modCount;

            /**
             * @return true if has next
             */
            @Override
            public final boolean hasNext() {
                return node != null;
            }

            /**
             * @return next value
             */
            @Override
            public final T next() {
                if (modCount != modCountSaved) {
                    throw new ConcurrentModificationException("Collection has been modified");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("No such");
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * @param <T> node type
     */
    private final static class Node<T> {
        /**
         * node value
         */
        T value;
        /**
         * next node
         */
        Node<T> next;

        /**
         * @param value - init value
         * @param next  - next node
         */
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}