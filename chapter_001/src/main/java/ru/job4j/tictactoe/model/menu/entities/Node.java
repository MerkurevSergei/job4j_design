package ru.job4j.tictactoe.model.menu.entities;

/**
 * The {@code Node} provide interface for menu node entities.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public interface Node {

    /**
     * @return node id.
     */
    String getId();

    /**
     * @return node name.
     */
    String getName();

    /**
     * @return node prefix for name.
     */
    String getPrefix();

    /**
     * @return children of the node.
     */
    Node[] getChildren();

}
