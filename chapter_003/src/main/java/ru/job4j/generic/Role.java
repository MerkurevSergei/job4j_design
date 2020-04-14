package ru.job4j.generic;

/**
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Role extends Base {
    /**
     * imitate empty element
     */
    final public static Role EMPTY = null;

    /**
     * @param id - entity id
     */
    public Role(String id) {
        super(id);
    }
}
