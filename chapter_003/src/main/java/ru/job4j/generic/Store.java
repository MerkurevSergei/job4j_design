package ru.job4j.generic;

/**
 * Store interface.
 *
 * @param <T> - type of stored elements extends of Base
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public interface Store<T extends Base> {

    /**
     * @param model - item to be stored
     */
    void add(T model);

    /**
     * @param id - id element to be replaced
     * @param model - replacement element
     * @return result - true if success
     */
    boolean replace(String id, T model);

    /**
     * @param id - id element to be deleted
     * @return result - true if success
     */
    boolean delete(String id);

    /**
     * @param id - id element from search
     * @return - the element
     */
    T findById(String id);
}