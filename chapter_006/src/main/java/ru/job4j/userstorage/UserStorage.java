package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code UserStorage} stores {@code User}.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
@ThreadSafe
public final class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> accounts = new HashMap<>();

    /**
     * @param user added.
     * @return true if user add.
     */
    public synchronized boolean add(final User user) {
        boolean result = false;
        if (accounts.get(user.getId()) == null) {
            accounts.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    /**
     * @param user updated
     * @return true if user update.
     */
    public synchronized boolean update(final User user) {
        boolean result = false;
        if (accounts.get(user.getId()) != null) {
            accounts.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    /**
     * @param user for deleted.
     * @return true if user delete.
     */
    public synchronized boolean delete(final User user) {
        return accounts.remove(user.getId(), user);
    }

    /**
     * @param fromId source user id.
     * @param toId destination user id.
     * @param amount for transfer.
     */
    public synchronized void transfer(int fromId, int toId, int amount) {
        final User userSrc = accounts.get(fromId);
        final User userDest = accounts.get(toId);
        userSrc.setAmount(userSrc.getAmount() - amount);
        userDest.setAmount(userDest.getAmount() + amount);
    }

    protected synchronized int size() {
        return accounts.size();
    }
}
