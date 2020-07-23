package ru.job4j.tictactoe.model.game.entities;

/**
 * The {@code Board} provide interface for used
 * in game as a board for the Tic-tac-toe game.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.3
 * @since 0.1
 */
public interface Board {

    /**
     * Get mark for a point on the board.
     *
     * @param point as array contains coordinates.
     * @return player as a mark for a point on the board.
     */
    Player getMark(int... point);

    /**
     * Set mark for a point on the board.
     *
     * @param player as a mark for a point on the board.
     * @param point  as an array contains coordinates.
     */
    void setMark(Player player, int... point);

    /**
     * @return size as an array contains coordinates.
     */
    int[] getSize();
}