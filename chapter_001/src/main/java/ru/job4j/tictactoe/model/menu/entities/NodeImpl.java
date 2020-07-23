package ru.job4j.tictactoe.model.menu.entities;

/**
 * The {@code NodeImpl} simple implements for {@code Node} interface.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public final class NodeImpl implements Node {

    /**
     * The node id.
     */
    private final String id;


    /**
     * The node name.
     */
    private final String name;

    /**
     * The node prefix.
     */
    private final String prefix;

    /**
     * The children of the node.
     */
    private final Node[] children;

    /**
     * @param id     init.
     * @param name   init.
     * @param prefix init.
     */
    public NodeImpl(String id, String name, String prefix) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.children = new Node[0];
    }

    /**
     * @param id       init.
     * @param name     init.
     * @param prefix   init.
     * @param children init.
     */
    public NodeImpl(String id, String name, String prefix, Node[] children) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.children = children;
    }

    /**
     * @return node id.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * @return node name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return node prefix for name.
     */
    @Override
    public String getPrefix() {
        return prefix;
    }

    /**
     * @return children of the node.
     */
    @Override
    public Node[] getChildren() {
        return children;
    }
}
