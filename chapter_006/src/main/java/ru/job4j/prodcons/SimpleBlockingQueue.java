package ru.job4j.prodcons;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The {@code SimpleBlockingQueue} is blocking queue.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Put to queue.
     * @param value by put.
     */
    public synchronized void offer(T value) {
        queue.offer(value);
        this.notifyAll();
    }

    /**
     * @return value from queue.
     * @throws InterruptedException if interrupt thread.
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.poll();
    }
}
