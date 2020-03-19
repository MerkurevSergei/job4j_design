package ru.job4j.tdd;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CinemaTest {

    @Test
    public void buyHasTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void buyHasNotTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(Ticket3D.EMPTY));
    }

    @Test
    public void findSuccess() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Collections.singletonList(new Session3D())));
    }

    @Test
    public void findFailure() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions, is(Collections.emptyList()));
    }

    @Test
    public void addSuccess() {
        Cinema3D cinema = new Cinema3D();
        cinema.add(new Session3D());
        assertTrue(cinema.addSuccess);
    }

    @Test
    public void addFailure() {
        Cinema3D cinema = new Cinema3D();
        assertFalse(cinema.addSuccess);
    }
}