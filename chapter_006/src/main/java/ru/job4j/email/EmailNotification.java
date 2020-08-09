package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The {@code EmailNotification} send email to users.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public final class EmailNotification {

    private final ExecutorService pool;

    /**
     * Create thread pool.
     */
    public EmailNotification() {
        pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Send email to user.
     *
     * @param user from send.
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification %s to email %s", user.getName(), user.getMail());
            String body = String.format("Add a new event to %s", user.getName());
            send(subject, body, user.getMail());
        });
    }

    /**
     * Close thread pool.
     */
    public void close() {
        pool.shutdown();
    }

    void send(String subject, String body, String email) {

    }
}
