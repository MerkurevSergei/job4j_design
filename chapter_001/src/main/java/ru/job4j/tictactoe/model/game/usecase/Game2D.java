package ru.job4j.tictactoe.model.game.usecase;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.job4j.tictactoe.model.game.entities.AI;
import ru.job4j.tictactoe.model.game.entities.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static ru.job4j.tictactoe.model.game.entities.Player.*;

/**
 * The {@code Game2D} implements {@code Game} interface as
 * a use case for the Tic-tac-toe 2D game, provide methods
 * and simplifies work with entities of this game.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.3
 * @since 0.1
 */
public final class Game2D implements Game {

    /**
     * The {@code Board} height.
     */
    private int boardHeight;

    /**
     * The {@code Board} length.
     */
    private int boardLength;

    /**
     * The {@code Board} which is suitable for this implementation {@code Game}.
     */
    private Board board;

    /**
     * The {@code AI} which is suitable for this implementation {@code Game}.
     */
    private AI ai;

    /**
     * Win length is continuous sequence of identical marks required to win.
     */
    private int winLength;


    /**
     * Player whose turn is first.
     */
    private Player firstPlayer;

    /**
     * @param height      init
     * @param length      init
     * @param winLength   init
     * @param firstPlayer init
     */
    public Game2D(int height, int length, int winLength, Player firstPlayer) {
        this.boardHeight = height;
        this.boardLength = length;
        this.winLength = winLength;
        this.firstPlayer = firstPlayer;
    }

    /**
     * Get size for {@code} Board.
     *
     * @return size as an array contains coordinates.
     */
    @Override
    public int[] getBoardSize() {
        return new int[] {boardHeight, boardLength};
    }

    /**
     * Set size for {@code} Board.
     *
     * @param size as an array contains coordinates.
     */
    @Override
    public void setBoardSize(@NotNull int... size) throws IllegalArgumentException {
        check2DPoint(size);
        if (size[0] == 0 || size[1] == 0) {
            throw new IllegalArgumentException("size id not valid: " + Arrays.toString(size) + "!");
        }
        this.boardHeight = size[0];
        this.boardLength = size[1];
        this.board = null;
        this.ai = null;
    }

    /**
     * Get the player whose turn is first in the game.
     *
     * @return player whose turn is first.
     */
    @Override
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    /**
     * Set the player whose turn is first in the game.
     *
     * @param player whose turn is first.
     */
    @Override
    public void setFirstPlayer(Player player) {
        this.firstPlayer = player;
        this.board = null;
        this.ai = null;
    }

    /**
     * @param winLength is continuous sequence of
     *                  identical marks required to win.
     */
    @Override
    public void setWinLength(int winLength) {
        this.winLength = winLength;
        this.board = null;
        this.ai = null;
    }

    /**
     * Get mark for a point on the board.
     *
     * @param point as array contains coordinates.
     * @return player as a mark for a point on the board.
     */
    @Override
    public Player getMark(int... point) throws IllegalArgumentException {
        checkGameInitialization();
        check2DPoint(point);
        checkPointOnBoard(point);
        return board.getMark(point);
    }

    /**
     * Set special mark {@code Player.HUMAN} for a point on the board.
     *
     * @param point as an array contains coordinates.
     */
    @Override
    public void setHumanMark(int... point) throws IllegalArgumentException {
        checkGameInitialization();
        check2DPoint(point);
        checkPointOnBoard(point);
        checkOccupiedPoint(point);
        board.setMark(HUMAN, point);
    }

    /**
     * Get winner player in the game.
     *
     * @return winner player in the game.
     */
    @Override
    public Player getWinner() {
        checkGameInitialization();
        boolean hasEmpty = false;
        Player winner = null;

        winnerFind:
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (winner != null) {
                    break winnerFind;
                }
                hasEmpty = hasEmpty || (board.getMark(i, j) == NOBODY);
                int iHeight = i + winLength;
                int jLength = j + winLength;
                winner = checkHorizontal(i, j, jLength);
                winner = (winner == null) ? checkVertical(i, j, iHeight) : winner;
                winner = (winner == null) ? checkDiagonalLU(i, j, iHeight, jLength) : winner;
                winner = (winner == null) ? checkDiagonalLB(i, j, iHeight, jLength) : winner;
                winner = (winner == NOBODY) ? null : winner;
            }
        }
        return (winner == null && !hasEmpty) ? NOBODY : winner;
    }

    /**
     * Creates and initializes a new game.
     */
    @Override
    public void start() {
        board = new Board2D(boardHeight, boardLength);
        ai = new AI2D(board, winLength);
    }

    /**
     * Performs an artificial intelligence move in the game.
     */
    @Override
    public void moveAI() {
        checkGameInitialization();
        final int[] point = ai.move();
        board.setMark(AI, point);
    }

    /* ************************************************************************
     * This code block contains a set of functions that calculate win player. *
     *************************************************************************/

    /**
     * Gets the winner horizontally.
     *
     * @param iStart  - y start coordinate of the {@code Board}.
     * @param jStart  - x start coordinate of the {@code Board}.
     * @param jLength - x = x + winLength, end sequence coordinate of the {@code Board}.
     * @return player the winner horizontally.
     */
    private @Nullable Player checkHorizontal(int iStart, int jStart, int jLength) {
        if (jLength > boardLength) {
            return null;
        }
        HashSet<Player> winners = new HashSet<>();
        for (int j = jStart; j < jLength; j++) {
            winners.add(board.getMark(iStart, j));
        }
        return winners.size() == 1 ? winners.iterator().next() : null;
    }

    /**
     * Gets the winner vertically.
     *
     * @param iStart  - y start coordinate of the {@code Board}.
     * @param jStart  - x start coordinate of the {@code Board}.
     * @param iHeight - y = y + winLength, end sequence coordinate of the {@code Board}.
     * @return player the winner vertically.
     */
    private @Nullable Player checkVertical(int iStart, int jStart, int iHeight) {
        if (iHeight > boardHeight) {
            return null;
        }
        HashSet<Player> winners = new HashSet<>();
        for (int i = iStart; i < iHeight; i++) {
            winners.add(board.getMark(i, jStart));
        }
        return winners.size() == 1 ? winners.iterator().next() : null;
    }

    /**
     * Gets the winner upper-left diagonally.
     *
     * @param iStart  - y start coordinate of the {@code Board}.
     * @param jStart  - x start coordinate of the {@code Board}.
     * @param iHeight - y = y + winLength, end sequence coordinate of the {@code Board}.
     * @param jLength - x = x + winLength, end sequence coordinate of the {@code Board}.
     * @return player the winner upper-left diagonally.
     */
    private @Nullable Player checkDiagonalLU(int iStart, int jStart, int iHeight, int jLength) {
        if (iHeight > boardHeight || jLength > boardLength) {
            return null;
        }
        HashSet<Player> winners = new HashSet<>();
        for (int i = iStart, j = jStart; i < iHeight && j < jLength; i++, j++) {
            winners.add(board.getMark(i, j));
        }
        return winners.size() == 1 ? winners.iterator().next() : null;
    }

    /**
     * Gets the winner bottom-left diagonally.
     *
     * @param iStart  - y start coordinate of the {@code Board}.
     * @param jStart  - x start coordinate of the {@code Board}.
     * @param iHeight - y = y + winLength, end sequence coordinate of the {@code Board}.
     * @param jLength - x = x + winLength, end sequence coordinate of the {@code Board}.
     * @return player the winner bottom-left diagonally.
     */
    private @Nullable Player checkDiagonalLB(int iStart, int jStart, int iHeight, int jLength) {
        if (iHeight > boardHeight || jLength > boardLength) {
            return null;
        }
        HashSet<Player> winners = new HashSet<>();
        for (int i = iHeight - 1, j = jStart; i >= iStart && j < jLength; i--, j++) {
            winners.add(board.getMark(i, j));
        }
        return winners.size() == 1 ? winners.iterator().next() : null;
    }

    /* ************************************************************************
     * This code block contains a set of functions that check point and game. *
     *************************************************************************/

    /**
     * Checks that the point is two dimensional.
     *
     * @param point to check.
     * @throws IllegalArgumentException if the point is not two dimensional.
     */
    private void check2DPoint(int... point) throws IllegalArgumentException {
        if (point == null || point.length != 2) {
            throw new IllegalArgumentException("The point is not 2D: " + Arrays.toString(point) + "!");
        }
    }

    /**
     * Checks that the point is not occupied.
     *
     * @param point to check.
     * @throws IllegalArgumentException if the point is already occupied.
     */
    private void checkOccupiedPoint(int... point) throws IllegalArgumentException {
        if (board.getMark(point) != NOBODY) {
            throw new IllegalArgumentException("The point is already occupied: " + Arrays.toString(point) + "!");
        }
    }

    /**
     * Checks that the point on the board.
     *
     * @param point to check.
     * @throws IllegalArgumentException if the point is not on the board.
     */
    private void checkPointOnBoard(@NotNull int... point) throws IllegalArgumentException {
        int i = point[0];
        int j = point[1];
        if (!(0 <= i && i < boardHeight) || !(0 <= j && j < boardLength)) {
            throw new IllegalArgumentException("The point is not on the board: " + Arrays.toString(point) + "!");
        }
    }

    /**
     * Checks that game initialize.
     *
     * @throws IllegalStateException if the board or ai not instance.
     */
    private void checkGameInitialization() throws IllegalStateException {
        if (board == null || ai == null) {
            throw new IllegalStateException("The game not initialization, call method start please!");
        }
    }
}