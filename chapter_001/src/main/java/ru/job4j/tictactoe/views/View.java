package ru.job4j.tictactoe.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The {@code View} provides interface for displays
 * model data and redirects user input to {@code Presenter}.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public interface View {

    /**
     * Provides user input.
     */
    BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Added data to view.
     *
     * @param id   data.
     * @param data for displays.
     */
    void put(String id, Object data);

    /**
     * Show added data and return user input.
     *
     * @return user input.
     */
    String show();

    /**
     * Show error.
     *
     * @return null or other special answer.
     */
    String showError();

    /**
     * Provides user input.
     *
     * @return user input.
     */
    default String ask() {
        String result;
        try {
            result = READER.readLine();
        } catch (IOException e) {
            result = null;
        }
        return result;
    }
}
