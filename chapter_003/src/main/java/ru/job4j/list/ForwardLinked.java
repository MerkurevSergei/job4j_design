package ru.job4j.list;

import java.io.PrintWriter;
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
        Node<T> newNode = new Node<>(value, null, null);
        if (head == null) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
        modCount++;
    }

    /**
     * deleted first item
     *
     * @return deleted item
     * @throws NoSuchElementException exception
     */
    public final T deleteFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("No such");
        }
        T deleted = head.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        modCount++;
        return deleted;
    }

    /**
     * deleted last item
     *
     * @return deleted item
     * @throws NoSuchElementException exception
     */
    public final T deleteLast() throws NoSuchElementException {
        if (tail == null) {
            throw new NoSuchElementException("No such");
        }
        T deleted = tail.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        modCount++;
        return deleted;
    }

    /**
     * revert forward linked list
     */
    public void revert() {
        Node<T> tmpHead = head;
        while (head != null) {
            Node<T> tmp = head;
            head = head.next;
            tmp.next = tmp.prev;
            tmp.prev = head;
        }
        head = tail;
        tail = tmpHead;
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
         * prev node
         */
        Node<T> prev;

        /**
         * @param value - init value
         * @param next  - next node
         * @param prev  - prev node
         */
        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}