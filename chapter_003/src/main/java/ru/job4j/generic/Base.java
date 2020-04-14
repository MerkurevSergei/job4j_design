package ru.job4j.generic;

/**
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version $Id$
 * @since 0.1
 * <p>
 * Base Entity.
 */
public abstract class Base {
    /**
     * base entity id
     */
    private final String id;

    /**
     * imitate empty element
     */
    final public static Base EMPTY = null;

    /**
     * @param id - initialize id
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * @return id entity
     */
    public String getId() {
        return id;
    }
}