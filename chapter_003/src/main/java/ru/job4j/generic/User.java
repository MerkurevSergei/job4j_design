package ru.job4j.generic;

/**
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class User extends Base {
    /**
     * imitate empty element
     */
    final public static User EMPTY = null;

    /**
     * @param id - entity id
     */
    public User(String id) {
        super(id);
    }
}
