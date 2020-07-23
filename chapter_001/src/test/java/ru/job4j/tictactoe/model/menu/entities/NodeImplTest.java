package ru.job4j.tictactoe.model.menu.entities;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class NodeImplTest {

    @Test
    public void getId() {
        Node node = new NodeImpl("1", "a", "0");
        assertEquals("1", node.getId());
    }

    @Test
    public void getName() {
        Node node = new NodeImpl("1", "a", "0");
        assertEquals("a", node.getName());
    }

    @Test
    public void getPrefix() {
        Node node = new NodeImpl("1", "a", "0");
        assertEquals("0", node.getPrefix());
    }

    @Test
    public void getChildren() {
        Node[] children = new Node[]{new NodeImpl("1", "a", "0")};
        Node node = new NodeImpl("1", "a", "0", children);
        assertArrayEquals(children, node.getChildren());
    }
}