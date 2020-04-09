package ru.job4j.io.chat;

import java.io.Closeable;

/**
 * Chat Logger
 */
public interface Logger extends Closeable {
    /**
     * write message if logger valid, else ignored message
     *
     * @param msg - the written message
     */
    void write(String msg);

    /**
     * @return true if Logger can write message
     */
    boolean valid();
}
