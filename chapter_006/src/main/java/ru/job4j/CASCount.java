package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * The {@code CASCount} is atomic non-blocking counter.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count;

    public CASCount() {
        count = new AtomicReference<>();
        count.set(0);
    }

    /**
     * increment count.
     */
    public void increment() {
        while (true) {
            int value = count.get();
            if (count.compareAndSet(value, value + 1)) {
                break;
            }
        }
    }

    /**
     * @return count value.
     */
    public int get() {
        return count.get();
    }
}
