package ru.job4j.tictactoe.services.router.usecase;

import org.junit.Test;
import org.mockito.Mockito;
import ru.job4j.tictactoe.model.game.usecase.Game;
import ru.job4j.tictactoe.model.game.usecase.Game2D;

import static org.junit.Assert.*;

public class RouterImplTest {

    @Test
    public void addRedirect() {
        final Router router = Mockito.mock(RouterImpl.class);
        router.addRedirect("test", "test");
        Mockito.verify(router).addRedirect("test", "test");
    }

    @Test
    public void setLinkAndGetRouteParts() {
        final Router routes = new RouterImpl();
        routes.addRedirect("menu/show/010", "menu/show/0");
        routes.setLink("menu/show/0");

        assertEquals("menu", routes.getPresenterId());
        assertEquals("show", routes.getMethodName());
        assertArrayEquals(new String[] {"0"}, routes.getParams());

        routes.setLink("menu/show/010");

        assertEquals("menu", routes.getPresenterId());
        assertEquals("show", routes.getMethodName());
        assertArrayEquals(new String[] {"0"}, routes.getParams());

        routes.setLink(null);

        assertEquals("menu", routes.getPresenterId());
        assertEquals("show", routes.getMethodName());
        assertArrayEquals(new String[] {"0"}, routes.getParams());
    }

    @Test(expected = NullPointerException.class)
    public void setLinkNullWithoutBack() {
        final Router routes = new RouterImpl();
        routes.setLink(null);
        fail();
    }
}