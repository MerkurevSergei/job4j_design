package ru.job4j.pool;

import ru.job4j.prodcons.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * The {@code ThreadPool} implemented simple thread pool.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    /**
     * Launch processes by the number of cores.
     */
    public ThreadPool() {
        final int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            final Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {

                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                }
            });
            thread.start();
            threads.add(thread);
        }
    }

    /**
     * @param job added.
     */
    public void work(Runnable job) {
        tasks.offer(job);
    }

    /**
     * Shutdown threads from thread pool.
     */
    public void shutdown() throws InterruptedException {
        while (!tasks.isEmpty()) {
            for (Thread thread : threads) {
                thread.interrupt();
                thread.join();
            }
        }
    }
}