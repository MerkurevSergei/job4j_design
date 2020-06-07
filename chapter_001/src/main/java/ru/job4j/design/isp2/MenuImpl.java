package ru.job4j.design.isp2;

import java.util.LinkedList;
import java.util.List;

/**
 * Menu interface.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public final class MenuImpl implements Menu {

    /**
     * The top menu element.
     */
    private final Item top;

    /**
     * @param item init.
     */
    public MenuImpl(Item item) {
        top = item;
    }

    /**
     * @param item added item.
     */
    @Override
    public void add(Item item) {
        add(item, top.getId());
    }

    /**
     * @param item     added item.
     * @param parentId id parent item.
     */
    @Override
    public void add(Item item, String parentId) {
        final Item parentItem = findNodeById(parentId);
        parentItem.addChild(item);
    }

    /**
     * @param id chosen item.
     * @return chosen item.
     */
    @Override
    public Item choose(String id) {
        return findNodeById(id);
    }

    /**
     * @return menu items.
     */
    @Override
    public List<Item> items() {
        LinkedList<Item> items = new LinkedList<>();
        LinkedList<Item> stack = new LinkedList<>();
        stack.add(top);
        while (!stack.isEmpty()) {
            final Item item = stack.poll();
            stack.addAll(0, item.getChildren());
            items.add(item);
        }
        return items;
    }

    /**
     * @param id item
     * @return founded item
     */
    private Item findNodeById(String id) {
        Item res = top;
        if (id == null) {
            return res;
        }
        LinkedList<Item> stack = new LinkedList<>(top.getChildren());
        while (!stack.isEmpty()) {
            final Item item = stack.poll();
            if (id.equals(item.getId())) {
                res = item;
                break;
            }
            stack.addAll(0, item.getChildren());
        }
        return res;
    }
}
