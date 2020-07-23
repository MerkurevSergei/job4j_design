package ru.job4j.tictactoe.model.game.usecase;

import org.junit.Test;
import org.mockito.Mockito;
import ru.job4j.tictactoe.model.game.entities.Board;
import ru.job4j.tictactoe.model.game.entities.Board2D;
import ru.job4j.tictactoe.model.game.entities.Player;

import static org.junit.Assert.*;

public class Game2DTest {

    @Test
    public void setAndGetBoardSize() {
        Game game = new Game2D(3, 3, 3, Player.AI);
        final int[] boardSize33 = game.getBoardSize();
        game.setBoardSize(2, 1);
        final int[] boardSize21 = game.getBoardSize();

        assertArrayEquals(new int[] {3, 3}, boardSize33);
        assertArrayEquals(new int[] {2, 1}, boardSize21);
    }

    @Test
    public void setAndGetFirstPlayer() {
        Game game = new Game2D(3, 3, 3, Player.AI);
        final Player firstAI = game.getFirstPlayer();
        game.setFirstPlayer(Player.HUMAN);

        assertEquals(Player.AI, firstAI);
        assertEquals(Player.HUMAN, game.getFirstPlayer());
    }

    @Test
    public void setHumanMarkAndGetMark() {
        Game game = new Game2D(3, 3, 3, Player.AI);
        game.start();
        game.setHumanMark(0, 0);
        assertEquals(Player.HUMAN, game.getMark(0, 0));
        assertEquals(Player.NOBODY, game.getMark(0, 1));
    }

    @Test(expected = IllegalStateException.class)
    public void getMarkWhenGameNotInitialize() {
        Game game = new Game2D(3, 3, 3, Player.AI);
        game.getMark(0, 0);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void setMarkWhenGameNotInitialize() {
        Game game = new Game2D(3, 3, 3, Player.AI);
        game.setHumanMark(0, 0);
        fail();
    }

    @Test
    public void getWinner() {
        Game game = new Game2D(3, 3, 3, Player.AI);
        game.start();
        assertNull(game.getWinner());
    }

    @Test(expected = IllegalStateException.class)
    public void getWinnerWhenGameNotInitialize() {
        Game game = new Game2D(3, 3, 3, Player.AI);
        assertNull(game.getWinner());
        fail();
    }

    @Test
    public void start() {
        final Game game = Mockito.mock(Game2D.class);
        game.start();
        Mockito.verify(game).start();
    }

    @Test
    public void moveAI() {
        final Game game = Mockito.mock(Game2D.class);
        game.moveAI();
        Mockito.verify(game).moveAI();
    }
}