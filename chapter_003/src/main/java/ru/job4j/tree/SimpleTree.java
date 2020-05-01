package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Simple tree implementation.
 * @param <E> value of node
 */
public interface SimpleTree<E> {

    /**
     * Add child node by parent.
     * @param parent - parent node
     * @param child - child node
     * @return true if success added
     */
    boolean add(E parent, E child);

    /**
     * Searches for a node by value.
     * @param value searched value
     * @return target node
     */
    Optional<Node<E>> findBy(E value);

    /**
     * Inner class Node consist value.
     * @param <E> value
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}
