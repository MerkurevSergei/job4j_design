package ru.job4j.io.locksmanager;

import java.nio.file.Path;
import java.util.Optional;

/**
 * The PathLock instance.
 * Locked path and release lock.
 * File locks are held on behalf of the entire Java virtual machine.
 * They are not suitable for controlling access to a file by multiple threads within the same virtual machine.
 * <p>
 * For OS Windows: experiments have shown that extended blocking locked file from a second read
 * even for multiple threads within the same virtual machine
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.2
 */
public final class InnerFileLock implements AutoCloseable {

    private final Path path;
    private final boolean shared;
    private int count;

    /**
     * @param shared {@code true} if is shared
     */
    InnerFileLock(Path path, boolean shared) {
        this.path = Optional.of(path).get().toAbsolutePath();
        this.shared = shared;
        this.count = 1;
    }

    /**
     * @return locked path to file
     */
    public synchronized Path path() {
        return path;
    }

    /**
     * Tells whether this lock is shared.
     *
     * @return {@code true} if lock is shared,
     * {@code false} if it is exclusive
     */
    synchronized boolean isShared() {
        return shared;
    }

    /**
     * @return count locks on file
     */
    synchronized int count() {
        return count;
    }

    /**
     * Increase count locks on file.
     *
     * @return this
     */
    synchronized InnerFileLock increase() {
        count++;
        return this;
    }

    /**
     * Decrease locks on file.
     */
    synchronized void decrease() {
        count--;
    }

    /**
     * Release lock.
     */
    @Override
    public void close() {
        synchronized (InnerFileLocker.MANAGER) {
            synchronized (this) {
                InnerFileLocker.MANAGER.releaseLock(this);
            }
        }
    }
}
