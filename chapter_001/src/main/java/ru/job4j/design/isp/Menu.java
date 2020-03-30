package ru.job4j.design.isp;

import java.util.List;

/**
 * Menu object.
 * Consist menu nodes and operations to manage menu.
 */
public interface Menu {
    /**
     * Add node to menu.
     *
     * @param newNode - new node
     * @param id      - id parent node
     * @return result node adding
     */
    boolean addNode(Node newNode, String id);

    /**
     * Execute command from id.
     * @param id - id node to execute command
     * @return - result of execute
     */
    boolean execute(String id);

    /**
     * Find node by id.
     *
     * @param id - desired node id
     * @return Node - searching result or Node.Empty if failed
     */
    Node findNode(String id);

    /**
     * Menu iterator.
     *
     * @return Menu iterator implements.
     */
    MenuIterator iterator();

    /**
     * Convert menu object ot list
     *
     * @return List object of nodes
     */
    List<Node> toList();
}
