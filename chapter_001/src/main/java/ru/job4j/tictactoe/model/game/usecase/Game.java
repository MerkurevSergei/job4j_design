package ru.job4j.tictactoe.model.game.usecase;

import ru.job4j.tictactoe.model.game.entities.Player;

/**
 * The {@code Game} interface is use case for the Tic-tac-toe game,
 * provide methods and simplifies work with entities of this game.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.3
 * @since 0.1
 */
public interface Game {

    /**
     * Get size for {@code Board}.
     *
     * @return size as an array contains coordinates.
     */
    int[] getBoardSize();

    /**
     * Set size for {@code Board}.
     *
     * @param size as an array contains coordinates.
     */
    void setBoardSize(int... size);

    /**
     * Get the player whose turn is first in the game.
     *
     * @return player whose turn is first.
     */
    Player getFirstPlayer();

    /**
     * Set the player whose turn is first in the game.
     *
     * @param player whose turn is first.
     */
    void setFirstPlayer(Player player);

    /**
     * @param winLength is continuous sequence of
     *                  identical marks required to win.
     */
    void setWinLength(int winLength);

    /**
     * Get mark for a point on the board.
     *
     * @param point as array contains coordinates.
     * @return player as a mark for a point on the board.
     */
    Player getMark(int... point);

    /**
     * Set special mark HUMAN for a point on the board.
     *
     * @param point as an array contains coordinates.
     */
    void setHumanMark(int... point);


    /**
     * Get winner player in the game.
     *
     * @return winner player in the game.
     */
    Player getWinner();

    /**
     * Creates and initializes a new game.
     */
    void start();

    /**
     * Performs an artificial intelligence move in the game.
     */
    void moveAI();


}