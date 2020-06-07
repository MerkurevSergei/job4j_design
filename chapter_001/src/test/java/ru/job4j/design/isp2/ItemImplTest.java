package ru.job4j.design.isp2;

import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ItemImplTest {

    @Test
    public void getName() {
        final ItemImpl item = new ItemImpl("1", "Point 1", () -> true);
        assertEquals("Point 1", item.getName());
    }

    @Test
    public void getId() {
        final ItemImpl item = new ItemImpl("1", "Point 1", () -> true);
        assertEquals("1", item.getId());
    }

    @Test
    public void addChild() {
        final ItemImpl item = new ItemImpl("1", "Point 1", () -> true);
        item.addChild(new ItemImpl("2", "Point 2", () -> true));
        assertEquals(1, item.getChildren().size());
    }

    @Test
    public void getCommand() {
        final Supplier<Boolean> command = () -> true;
        final ItemImpl item = new ItemImpl("1", "Point 1", command);
        assertEquals(command, item.getCommand());
    }

    @Test
    public void getChildren() {
        final ItemImpl item = new ItemImpl("1", "Point 1", () -> true);
        item.addChild(new ItemImpl("2", "Point 2", () -> true));
        assertFalse(item.getChildren().isEmpty());
    }

    @Test
    public void testToString() {
        final ItemImpl itemTop = new ItemImpl(null, "Main", () -> true);
        final ItemImpl item = new ItemImpl("1", "Point 1", () -> true);
        assertEquals("Main", itemTop.toString());
        assertEquals("1. Point 1", item.toString());
    }
}