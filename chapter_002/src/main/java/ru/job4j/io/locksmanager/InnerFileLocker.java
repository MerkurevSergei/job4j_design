package ru.job4j.io.locksmanager;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The PathsLocker manages paths access, singleton.
 * Storage PathLocks in Map and lock path if it possible
 * Controlling access to a file by multiple threads within the same virtual machine.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public enum InnerFileLocker {

    /**
     * PathLocker singleton instance.
     */
    MANAGER;

    /**
     * Number of locks per file.
     */
    private final Map<Path, InnerFileLock> counter;

    /**
     * Private enumeration constructor.
     */
    InnerFileLocker() {
        counter = new HashMap<>();
    }

    /**
     * Lock path is possible or throw exception.
     *
     * @param path   init.
     * @param shared {@code true} if shared
     * @return InnerFileLock if path locked or null.
     */
    public synchronized InnerFileLock tryLock(Path path, boolean shared) {
        path = Optional.of(path).get().toAbsolutePath();
        InnerFileLock lock = counter.getOrDefault(path, null);
        if (lock != null && (!lock.isShared() || !shared)) {
            return null;
        }
        lock = (lock == null) ? new InnerFileLock(path, shared) : lock.increase();
        counter.put(path, lock);
        return lock;
    }

    /**
     * @param lock released
     */
    synchronized void releaseLock(InnerFileLock lock) {
        lock = Optional.of(lock).get();
        if (lock.count() == 0 || !counter.containsKey(lock.path())) {
            throw new IllegalArgumentException("InnerFileLocker not consist lock on file: " + lock.path());
        }
        if (lock.count() == 1) {
            counter.remove(lock.path());
        }
        lock.decrease();
    }
}