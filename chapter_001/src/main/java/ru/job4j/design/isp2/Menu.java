package ru.job4j.design.isp2;

import java.util.List;

/**
 * Menu interface.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public interface Menu {

    /**
     * @param item added item.
     */
    void add(Item item);

    /**
     * @param item     added item.
     * @param parentId id parent item.
     */
    void add(Item item, String parentId);

    /**
     * @param id chosen item.
     * @return chosen item.
     */
    Item choose(String id);

    /**
     * @return menu items.
     */
    List<Item> items();
}
