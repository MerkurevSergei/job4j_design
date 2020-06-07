package ru.job4j.design.isp2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Item for Menu implementation.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class ItemImpl implements Item {

    /**
     * item id.
     */
    private final String id;

    /**
     * item name.
     */
    private final String name;

    /**
     * item action.
     */
    private final Supplier<Boolean> command;

    /**
     * item children.
     */
    private final List<Item> children;

    /**
     * @param id      init
     * @param name    init
     * @param command init
     */
    public ItemImpl(String id, String name, Supplier<Boolean> command) {
        this.id = id;
        this.name = name;
        this.command = command;
        this.children = new ArrayList<>();
    }

    /**
     * @return item name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return item id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param item added item.
     */
    public void addChild(Item item) {
        children.add(item);
    }

    /**
     * @return item action.
     */
    public Supplier<Boolean> getCommand() {
        return command;
    }

    /**
     * @return item children.
     */
    public List<Item> getChildren() {
        return children;
    }

    /**
     * @return item presentation.
     */
    @Override
    public String toString() {
        String res;
        if (id == null) {
            res = name;
        } else {
            res = id + ". " + name;
        }
        return res;
    }
}
