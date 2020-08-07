package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * The {@code CounterBarrier}
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
@ThreadSafe
public class CountBarrier {

    private final Object monitor = this;

    private final int total;

    @GuardedBy("monitor")
    private int count = 0;

    /**
     * @param total init.
     */
    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * increment count.
     */
    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }

    }

    /**
     * await while count != total.
     */
    public void await() {
        synchronized (monitor) {
            try {
                while (count < total) {
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}