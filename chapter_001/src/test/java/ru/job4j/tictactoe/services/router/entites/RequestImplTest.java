package ru.job4j.tictactoe.services.router.entites;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestImplTest {

    @Test
    public void getRequestParts() {
        final RequestImpl request = new RequestImpl("menu/show/0");
        assertEquals("menu", request.getPresenterId());
        assertEquals("show", request.getMethodName());
        assertArrayEquals(new String[] {"0"}, request.getParams());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getRequestPartsInvalid() {
        final RequestImpl request = new RequestImpl("menu");
        assertEquals("menu", request.getPresenterId());
        assertEquals("show", request.getMethodName());
        assertArrayEquals(new String[] {"0"}, request.getParams());
        fail();
    }

}