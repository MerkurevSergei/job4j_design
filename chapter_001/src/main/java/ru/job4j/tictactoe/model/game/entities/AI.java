package ru.job4j.tictactoe.model.game.entities;

/**
 * The {@code AI} provide interface for Artificial Intelligence for the
 * Tic-tac-toe game. Suppose that all information for {@code move} function
 * consist in instance which create from class, which implementation this
 * interface.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public interface AI {

    /**
     * @return an array contains coordinates for
     * next move of the AI for the {@code Board} instance.
     */
    int[] move();
}