package ru.job4j.tree;

import java.util.*;

/**
 * Tree implement Simple tree.
 * @param <E> value of node
 */
class Tree<E> implements SimpleTree<E> {
    /**
     * Root node of tree.
     */
    private final Node<E> root;

    /**
     * @param root - value for root node
     */
    Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Add node to tree.
     * @param parent - parent node
     * @param child  - child node
     * @return - true if add success
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentOptNode = findBy(parent);
        if (parentOptNode.isEmpty()) {
            return false;
        }
        Optional<Node<E>> childOptNode = findBy(child);
        if (childOptNode.isEmpty()) {
            Node<E> parentNode = parentOptNode.get();
            parentNode.children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    /**
     * Searches for a node by value.
     * @param value searched value
     * @return target node
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}