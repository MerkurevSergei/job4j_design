package ru.job4j.generic;

import java.util.Iterator;

/**
 * RoleStore is implements AbstractStore.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class RoleStore extends AbstractStore<Role> {
    /**
     * @param size -max size of store
     */
    public RoleStore(int size) {
        super(size);
    }
}
