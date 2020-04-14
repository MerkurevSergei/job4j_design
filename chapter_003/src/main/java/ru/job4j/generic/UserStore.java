package ru.job4j.generic;

import java.util.Iterator;

/**
 * UserStore is implements AbstractStore.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class UserStore extends AbstractStore<User> {
    /**
     * @param size -max size of store
     */
    public UserStore(int size) {
        super(size);
    }
}
