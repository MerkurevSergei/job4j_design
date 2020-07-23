package ru.job4j.tictactoe.model.game.entities;

import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static ru.job4j.tictactoe.model.game.entities.Player.AI;
import static ru.job4j.tictactoe.model.game.entities.Player.*;


/**
 * The {@code AI2D} implementation of the {@code AI} interface. This
 * implementation provides Artificial Intelligence for the 2D Tic-tac-toe
 * game and used {@code Board} interface.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public final class AI2D implements AI {

    /**
     * The {@code Board} which is suitable for this implementation AI.
     */
    private final Board board;

    /**
     * The {@code Board} height.
     */
    private final int height;

    /**
     * The {@code Board} length.
     */
    private final int length;

    /**
     * Length of a continuous sequence of identical marks.
     */
    private final int winLength;

    /**
     * The Auxiliary table to calculate the weights of the cells {@code Board}.
     */
    private int[][] scores;

    /**
     * The special function that is used for making an part estimate in {@code scores}.
     */
    Map<String, Integer> evalFunction;

    /**
     * @param board     game init
     * @param winLength init
     */
    public AI2D(@NotNull Board board, int winLength) {
        this.board = board;
        this.height = board.getSize()[0];
        this.length = board.getSize()[1];
        this.winLength = winLength;
        this.scores = new int[height][length];
        this.evalFunction = initEvalFunction();
    }

    /**
     * @return the special function that is used
     * for making an part estimate in {@code scores}.
     */
    private @NotNull Map<String, Integer> initEvalFunction() {
        Map<String, Integer> function = new HashMap<>();
        function.put("[0, " + (winLength) + ", 0]", 9999);
        function.put("[0, " + (winLength - 1) + ", 1]", 100);
        function.put("[1, " + (winLength - 1) + ", 0]", 100);
        return function;
    }

    /**
     * @return an array contains coordinates for
     * next move of the AI for the {@code Board} instance.
     */
    @Contract(" -> new")
    @Override
    public int @NotNull [] move() {
        scores = new int[height][length];
        calculateScores();
        int m = -1;
        int n = -1;
        int score = -1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (score < scores[i][j]) {
                    m = i;
                    n = j;
                    score = scores[i][j];
                }
            }
        }
        return new int[]{m, n};
    }

    /**
     * Basic function which calls other function for calculate
     * an estimate for {@code Board}.
     */
    private void calculateScores() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                setInitialScores(i, j);
                addFunctionScores(i, j);
            }
        }
    }

    /* ************************************************************************
     * This code block contains a set of functions that calculates initial
     * scores for AI decision making.
     *************************************************************************/

    /**
     * Calculate and set initials scores.
     *
     * @param i - y coordinate of the {@code Board}.
     * @param j - x coordinate of the {@code Board}.
     */
    private void setInitialScores(int i, int j) {
        if (board.getMark(i, j) != NOBODY) {
            scores[i][j] = -1;
            return;
        }
        setPartScoreHoriz(i, j, getPartScoreHoriz(i, j));
        setPartScoreVert(i, j, getPartScoreVert(i, j));
        setPartScoreDiagLU(i, j, getPartScoreDiagLU(i, j));
        setPartScoreDiagLB(i, j, getPartScoreDiagLB(i, j));
    }

    /**
     * Get initial scores for cells with {x, y} - {x + winLength, y}
     * coordinates from horizontal lines.
     *
     * @param iStart - y coordinate of the {@code Board}.
     * @param jStart - x coordinate of the {@code Board}.
     * @return scores for cell with {x, y} coordinates.
     */
    private int getPartScoreHoriz(int iStart, int jStart) {
        int result = 0;
        int jLength = jStart + winLength;
        if (jLength > length) {
            return 0;
        }
        for (int j = jStart; j < jLength; j++) {
            if (localCellScore(iStart, j) == -1) {
                result = 0;
                break;
            }
            result += localCellScore(iStart, j);
        }
        return result;
    }

    /**
     * Set initial scores from function {@code getPartScoreHoriz} for cells
     * with {x, y} - {x + winLength, y} coordinates from horizontal lines.
     *
     * @param iStart - y coordinate of the {@code Board}.
     * @param jStart - x coordinate of the {@code Board}.
     * @param score  for cells with with {x, y} - {x + winLength, y} coordinates.
     */
    private void setPartScoreHoriz(int iStart, int jStart, int score) {
        int jLength = jStart + winLength;
        jLength = Math.min(jLength, length);
        for (int j = jStart; j < jLength; j++) {
            scores[iStart][j] += score;
        }
    }

    /**
     * Get initial scores for cells with {x, y} - {x, y + winLength}
     * coordinates from vertical lines.
     *
     * @param iStart - y coordinate of the {@code Board}.
     * @param jStart - x coordinate of the {@code Board}.
     * @return scores for cell with {x, y} coordinates.
     */
    private int getPartScoreVert(int iStart, int jStart) {
        int result = 0;
        int iHeight = iStart + winLength;
        if (iHeight > height) {
            return 0;
        }
        for (int i = iStart; i < iHeight; i++) {
            if (localCellScore(i, jStart) == -1) {
                result = 0;
                break;
            }
            result += localCellScore(i, jStart);
        }
        return result;
    }

    /**
     * Set initial scores from function {@code getPartScoreVert} for cells
     * with {x, y} - {x, y + winLength} coordinates from vertical lines.
     *
     * @param iStart - y coordinate of the {@code Board}.
     * @param jStart - x coordinate of the {@code Board}.
     * @param score  for cells with with {x, y} - {x, y + winLength} coordinates.
     */
    private void setPartScoreVert(int iStart, int jStart, int score) {
        int iHeight = iStart + winLength;
        iHeight = Math.min(iHeight, height);
        for (int i = iStart; i < iHeight; i++) {
            scores[i][jStart] += score;
        }
    }

    /**
     * Get initial scores for cell with {x, y} - {x + winLength, y + winLength}
     * coordinates from upper-left diagonal.
     *
     * @param iStart - y coordinate of the {@code Board}.
     * @param jStart - x coordinate of the {@code Board}.
     * @return scores for cell with {x, y} coordinates.
     */
    private int getPartScoreDiagLU(int iStart, int jStart) {
        int result = 0;
        int iHeight = iStart + winLength;
        int jLength = jStart + winLength;
        if (iHeight > height || jLength > length) {
            return 0;
        }
        for (int i = iStart, j = jStart; i < iHeight && j < jLength; i++, j++) {
            if (localCellScore(i, j) == -1) {
                result = 0;
                break;
            }
            result += localCellScore(i, j);
        }
        return result;
    }

    /**
     * Set initial scores from function {@code getPartScoreDiagLU} for cells with
     * {x, y} - {x + winLength, y + winLength} coordinates from upper-left diagonal.
     *
     * @param iStart - y coordinate of the {@code Board}.
     * @param jStart - x coordinate of the {@code Board}.
     * @param score  for cells with with {x, y} - {x + winLength, y + winLength} coordinates.
     */
    private void setPartScoreDiagLU(int iStart, int jStart, int score) {
        int iHeight = iStart + winLength;
        int jLength = jStart + winLength;
        iHeight = Math.min(iHeight, height);
        jLength = Math.min(jLength, length);
        for (int i = iStart, j = jStart; i < iHeight && j < jLength; i++, j++) {
            scores[i][j] += score;
        }
    }

    /**
     * Get initial scores for cell with {x, y} - {x + winLength, y + winLength}
     * coordinates from bottom-left diagonal.
     *
     * @param iStart - y coordinate of the {@code Board}.
     * @param jStart - x coordinate of the {@code Board}.
     * @return scores for cell with {x, y} coordinates.
     */
    private int getPartScoreDiagLB(int iStart, int jStart) {
        int result = 0;
        int iHeight = iStart + winLength;
        int jLength = jStart + winLength;
        if (iHeight > height || jLength > length) {
            return 0;
        }
        for (int i = iHeight - 1, j = jStart; i >= iStart && j < jLength; i--, j++) {
            if (localCellScore(i, j) == -1) {
                result = 0;
                break;
            }
            result += localCellScore(i, j);
        }
        return result;
    }

    /**
     * Set initial scores from function {@code getPartScoreDiagLB} for cells with
     * {x, y} - {x + winLength, y + winLength} coordinates from bottom-left diagonal.
     *
     * @param iStart - y coordinate of the {@code Board}.
     * @param jStart - x coordinate of the {@code Board}.
     * @param score  for cells with with {x, y} - {x + winLength, y + winLength} coordinates.
     */
    private void setPartScoreDiagLB(int iStart, int jStart, int score) {
        int iHeight = iStart + winLength;
        int jLength = jStart + winLength;
        iHeight = Math.min(iHeight, height);
        jLength = Math.min(jLength, length);
        for (int i = iHeight - 1, j = jStart; i >= iStart && j < jLength; i--, j++) {
            scores[i][j] += score;
        }
    }

    /**
     * @param i - y coordinate of the {@code Board}.
     * @param j - x coordinate of the {@code Board}.
     * @return an estimate from local cell with {x, y} coordinates.
     */
    private int localCellScore(int i, int j) {
        final Player current = board.getMark(i, j);
        int score = -1;
        score = (current == AI) ? 2 : score;
        score = (current == NOBODY) ? 1 : score;
        return score;
    }

    /* ************************************************************************
     * This code block contains a set of functions that calculates additional
     * values for AI decision making
     *************************************************************************/

    /**
     * Calculate and set additional scores.
     *
     * @param iStart - y coordinate of the {@code Board}.
     * @param jStart - x coordinate of the {@code Board}.
     */
    private void addFunctionScores(int iStart, int jStart) {
        if (board.getMark(iStart, jStart) != NOBODY) {
            return;
        }
        int iBottom = Math.max(iStart - winLength, 0);
        int iTop = Math.min(iStart + winLength - 1, height - 1);
        int jLeft = Math.max(jStart - winLength, 0);
        int jRight = Math.min(jStart + winLength - 1, length - 1);
        scores[iStart][jStart] += NumberUtils.max(vScore(iStart, jStart, iBottom, iTop, AI),
                vScore(iStart, jStart, iBottom, iTop, HUMAN),
                hScore(iStart, jStart, jLeft, jRight, AI),
                hScore(iStart, jStart, jLeft, jRight, HUMAN),
                diagULScore(iStart, jStart, iBottom, iTop, jLeft, jRight, AI),
                diagULScore(iStart, jStart, iBottom, iTop, jLeft, jRight, HUMAN),
                diagBLScore(iStart, jStart, iBottom, iTop, jLeft, jRight, AI),
                diagBLScore(iStart, jStart, iBottom, iTop, jLeft, jRight, HUMAN));
    }

    /**
     * Calculate additional scores for cells
     * with {x, y} coordinates from vertical lines.
     *
     * @param iStart  - y start coordinate of the {@code Board}.
     * @param jStart  - x start coordinate of the {@code Board}.
     * @param iBottom - y = iStart - winLength, bottom coordinate of the {@code Board}.
     * @param iTop    - y = iStart + winLength, top coordinate of the {@code Board}.
     * @param player  from evaluate.
     * @return score for cells with {x, y} coordinates.
     */
    private int vScore(int iStart, int jStart, int iBottom, int iTop, Player player) {
        int[] count = new int[3];
        count[1]++;
        int i = iStart;
        while (--i >= iBottom && board.getMark(i, jStart) == player) {
            count[1]++;
        }
        while (i >= iBottom && board.getMark(i, jStart) == NOBODY) {
            count[0]++;
            i--;
        }
        i = iStart;
        while (++i <= iTop && board.getMark(i, jStart) == player) {
            count[1]++;
        }
        while (i <= iTop && board.getMark(i, jStart) == NOBODY) {
            count[2]++;
            i++;
        }
        return evalFunction.getOrDefault(Arrays.toString(count), 0);
    }

    /**
     * Calculate additional scores for cells
     * with {x, y} coordinates from horizontal lines.
     *
     * @param iStart - y start coordinate of the {@code Board}.
     * @param jStart - x start coordinate of the {@code Board}.
     * @param jLeft  - x = x - winLength, left coordinate of the {@code Board}.
     * @param jRight - x = x + winLength, right coordinate of the {@code Board}.
     * @param player from evaluate.
     * @return score for cells with {x, y} coordinates.
     */
    private int hScore(int iStart, int jStart, int jLeft, int jRight, Player player) {
        int[] count = new int[3];
        count[1]++;
        int j = jStart;
        while (--j >= jLeft && board.getMark(iStart, j) == player) {
            count[1]++;
        }
        while (j >= jLeft && board.getMark(iStart, j) == NOBODY) {
            count[0]++;
            j--;
        }
        j = jStart;
        while (++j <= jRight && board.getMark(iStart, j) == player) {
            count[1]++;
        }
        while (j <= jRight && board.getMark(iStart, j) == NOBODY) {
            count[2]++;
            j++;
        }
        return evalFunction.getOrDefault(Arrays.toString(count), 0);
    }

    /**
     * Calculate additional scores for cells
     * with {x, y} coordinates from upper-left diagonal.
     *
     * @param iStart  - y start coordinate of the {@code Board}.
     * @param jStart  - x start coordinate of the {@code Board}.
     * @param iBottom - y = iStart - winLength, bottom coordinate of the {@code Board}.
     * @param iTop    - y = iStart + winLength, top coordinate of the {@code Board}.
     * @param jLeft   - x = x - winLength, left coordinate of the {@code Board}.
     * @param jRight  - x = x + winLength, right coordinate of the {@code Board}.
     * @param player  from evaluate.
     * @return score for cells with {x, y} coordinates.
     */
    private int diagULScore(int iStart, int jStart, int iBottom, int iTop, int jLeft, int jRight, Player player) {
        int[] count = new int[3];
        count[1]++;
        int i = iStart;
        int j = jStart;
        while (--i >= iBottom && --j >= jLeft && board.getMark(i, j) == player) {
            count[1]++;
        }
        while (i >= iBottom && j >= jLeft && board.getMark(i, j) == NOBODY) {
            count[0]++;
            i--;
            j--;
        }
        i = iStart;
        j = jStart;
        while (++i <= iTop && ++j <= jRight && board.getMark(i, j) == player) {
            count[1]++;
        }
        while (i <= iTop && j <= jRight && board.getMark(i, j) == NOBODY) {
            count[2]++;
            i++;
            j++;
        }
        return evalFunction.getOrDefault(Arrays.toString(count), 0);
    }

    /**
     * Calculate additional scores for cells
     * with {x, y} coordinates from bottom-left diagonal.
     *
     * @param iStart  - y start coordinate of the {@code Board}.
     * @param jStart  - x start coordinate of the {@code Board}.
     * @param iBottom - y = iStart - winLength, bottom coordinate of the {@code Board}.
     * @param iTop    - y = iStart + winLength, top coordinate of the {@code Board}.
     * @param jLeft   - x = x - winLength, left coordinate of the {@code Board}.
     * @param jRight  - x = x + winLength, right coordinate of the {@code Board}.
     * @param player  from evaluate.
     * @return score for cells with {x, y} coordinates.
     */
    private int diagBLScore(int iStart, int jStart, int iBottom, int iTop, int jLeft, int jRight, Player player) {
        int[] count = new int[3];
        count[1]++;
        int i = iStart;
        int j = jStart;
        while (++i <= iTop && --j >= jLeft && board.getMark(i, j) == player) {
            count[1]++;
        }
        while (i <= iTop && j >= jLeft && board.getMark(i, j) == NOBODY) {
            count[0]++;
            i++;
            j--;
        }
        i = iStart;
        j = jStart;
        while (--i >= iBottom && ++j <= jRight && board.getMark(i, j) == player) {
            count[1]++;
        }
        while (i >= iBottom && j <= jRight && board.getMark(i, j) == NOBODY) {
            count[2]++;
            i--;
            j++;
        }
        return evalFunction.getOrDefault(Arrays.toString(count), 0);
    }
}