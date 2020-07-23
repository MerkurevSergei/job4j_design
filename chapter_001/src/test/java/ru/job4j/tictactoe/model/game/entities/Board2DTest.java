package ru.job4j.tictactoe.model.game.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class Board2DTest {

    @Test
    public void setAndGetMark() {
        Board board = new Board2D(2, 3);
        board.setMark(Player.AI, 1, 1);
        board.setMark(Player.HUMAN, 1, 2);

        assertEquals(Player.NOBODY, board.getMark(0, 0));
        assertEquals(Player.AI, board.getMark(1, 1));
        assertEquals(Player.HUMAN, board.getMark(1, 2));
    }

    @Test
    public void getSize() {
        assertArrayEquals(new int[]{2, 3}, new Board2D(2, 3).getSize());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenIllegalSize() {
        assertArrayEquals(new int[]{0, 0}, new Board2D(0, 0).getSize());
        fail();
    }
}