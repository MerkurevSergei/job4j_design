package ru.job4j.tictactoe.services.router.entites;

import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectRouteImplTest {

    @Test
    public void getSourceLink() {
        RedirectRoute link = new RedirectRouteImpl("menu/show/0", "menu/show/1");
        assertEquals("menu/show/1", link.getDestinationLink());
    }

    @Test
    public void getDestinationLink() {
        RedirectRoute link = new RedirectRouteImpl("menu/show/0", "menu/show/1");
        assertEquals("menu/show/1", link.getDestinationLink());
    }
}