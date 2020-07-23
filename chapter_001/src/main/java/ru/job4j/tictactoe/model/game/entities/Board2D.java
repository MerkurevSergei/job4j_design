package ru.job4j.tictactoe.model.game.entities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code Board2D} implements interface {@code Board}
 * for used in game as a board for the Tic-tac-toe game.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.3
 * @since 0.1
 */
public final class Board2D implements Board {

    /**
     * Array representation board,
     * contains mark Players on the board.
     */
    private final Player[][] data;

    /**
     * @param height of the array data.
     * @param length of the array data.
     */
    public Board2D(int height, int length) {
        data = new Player[height][length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = Player.NOBODY;
            }
        }
    }

    /**
     * Get mark for a point on the board.
     *
     * @param point as array contains coordinates.
     * @return player as a mark for a point on the board.
     */
    @Contract(pure = true)
    @Override
    public Player getMark(@NotNull int... point) {
        int i = point[0];
        int j = point[1];
        return data[i][j];
    }

    /**
     * Set mark for a point on the board.
     *
     * @param player as a mark for a point on the board.
     * @param point  as an array contains coordinates.
     */
    @Override
    public void setMark(Player player, @NotNull int... point) {
        int i = point[0];
        int j = point[1];
        data[i][j] = player;
    }

    /**
     * @return size as an array contains coordinates.
     */
    @Contract(value = " -> new", pure = true)
    @Override
    public int @NotNull [] getSize() {
        return new int[]{data.length, data[0].length};
    }
}