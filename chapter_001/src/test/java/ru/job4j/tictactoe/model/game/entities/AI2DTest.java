package ru.job4j.tictactoe.model.game.entities;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class AI2DTest {

    @Test
    public void move() {
        Board board = new Board2D(3, 3);
        AI ai = new AI2D(board, 3);
        assertArrayEquals(new int[]{1, 1}, ai.move());
    }
}