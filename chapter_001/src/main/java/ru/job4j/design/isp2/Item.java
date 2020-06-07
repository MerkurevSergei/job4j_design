package ru.job4j.design.isp2;

import java.util.List;
import java.util.function.Supplier;

/**
 * Item for Menu interface.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public interface Item {

    /**
     * @return item name.
     */
    String getName();

    /**
     * @return item id.
     */
    String getId();

    /**
     * @param item added item.
     */
    void addChild(Item item);

    /**
     * @return item action.
     */
    Supplier<Boolean> getCommand();

    /**
     * @return item children.
     */
    List<Item> getChildren();
}
