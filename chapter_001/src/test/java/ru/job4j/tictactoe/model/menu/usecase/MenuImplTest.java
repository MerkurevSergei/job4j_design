package ru.job4j.tictactoe.model.menu.usecase;

import org.junit.Test;
import ru.job4j.tictactoe.model.menu.entities.Node;
import ru.job4j.tictactoe.model.menu.entities.NodeImpl;

import static org.junit.Assert.*;

public class MenuImplTest {

    @Test
    public void findById() {
        Node node = new NodeImpl("011", "AI", "1");
        Menu menu = new MenuImpl(
                new NodeImpl("0", "GAME MENU", "0", new NodeImpl[]{
                        new NodeImpl("01", "First move", "1", new Node[]{
                                node,
                        }),
                })
        );
        assertEquals(node, menu.findById("011"));
        assertNull(menu.findById("012"));
    }
}