package ru.job4j.design.isp;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Menu iterator implementation.
 */
public interface MenuIterator extends Iterator<Node> {
    /**
     * Get next node.
     *
     * @return Node next
     */
    Node next();

    /**
     * Check has next node.
     *
     * @return has next node result
     */
    boolean hasNext();

    /**
     * Reset iterator state to top.
     */
    void reset();

    /**
     * Remove node unsupported
     */
    @Override
    default void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param action action
     */
    @Override
    default void forEachRemaining(Consumer<? super Node> action) {
        throw new UnsupportedOperationException();
    }
}
