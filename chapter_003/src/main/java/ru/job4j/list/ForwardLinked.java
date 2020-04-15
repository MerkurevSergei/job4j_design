package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Forward Linked store
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class ForwardLinked<T> implements Iterable<T> {
    /**
     * top item
     */
    private Node<T> head;

    /**
     * @param value - value to be stored
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * deleted first element
     * @return deleted item
     * @throws NoSuchElementException exception
     */
    public T deleteFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("No such");
        }
        Node<T> deleted = head;
        head = head.next;
        return deleted.value;
    }

    /**
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            /**
             * set iterable node to head
             */
            Node<T> node = head;

            /**
             * @return true if has next
             */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * @return next value
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
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
    private static class Node<T> {
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
         * @param next - next node
         */
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}