package ru.job4j.design.isp;

import java.util.ListIterator;

/**
 * Node object.
 * As a one unit from tree
 */
public interface Node extends Command {

    /**
     * Empty command.
     */
    Node EMPTY = null;

    /**
     * Get id node.
     * @return String - this id node
     */
    String getId();

    /**
     * Get name node.
     * @return String - this name node
     */
    String getName();

    /**
     * Add children node to this node.
     * @param node - child node
     */
    void addChildren(Node node);

    /**
     * Get iterator on children node.
     * @return ListIterator of children nodes
     */
    ListIterator<Node> childIterator();

    /**
     * Check consist children nodes.
     * @return boolean - consist children node
     */
    boolean hasChildren();

}
