package ru.job4j.tdd;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Cinema stub interface implementation,
 * from test pass.
 */
public class Cinema3D implements Cinema {

    public boolean addSuccess = false;

    /**
     * @param filter - predicate
     * @return Sessions success predicate
     */
    @Override
    public List<Session> find(Predicate<Session> filter) {
        List<Session> rsl;
        if (filter.test(new Session3D())) {
            rsl = Collections.singletonList(new Session3D());
        } else {
            rsl = Collections.emptyList();
        }

        return rsl;
    }

    /**
     *
     * @param account - client account
     * @param row - wish row
     * @param column - wish column
     * @param date - wish date
     * @return Ticket
     */
    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return Ticket3D.EMPTY;
    }

    /**
     *
     * @param session - add session
     */
    @Override
    public void add(Session session) {
        addSuccess = true;
    }
}
