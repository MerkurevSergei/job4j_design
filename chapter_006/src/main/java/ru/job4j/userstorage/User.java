package ru.job4j.userstorage;

import net.jcip.annotations.ThreadSafe;

/**
 * The {@code User} is POJO saved user id and amount.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
@ThreadSafe
public final class User {

    private final int id;
    private volatile int amount;

    /**
     * @param id     init.
     * @param amount init.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * @return {@code User} id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return {@code User} amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount new value.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
