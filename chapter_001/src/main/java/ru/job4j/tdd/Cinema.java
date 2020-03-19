package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Cinema interface.
 * Provides an opportunity find, buy and add.
 */
public interface Cinema {

    /**
     * @param filter - predicate
     * @return Sessions success predicate
     */
    List<Session> find(Predicate<Session> filter);

    /**
     * @param account - client account
     * @param row - wish row
     * @param column - wish column
     * @param date - wish date
     * @return Ticket
     */
    Ticket buy(Account account, int row, int column, Calendar date);

    /**
     * @param session - add session
     */
    void add(Session session);
}