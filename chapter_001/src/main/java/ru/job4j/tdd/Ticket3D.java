package ru.job4j.tdd;

import java.util.Objects;

/**
 * Ticket stub implementation.
 */
public class Ticket3D implements Ticket {
    public static final Ticket EMPTY = new Ticket3D();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return  true;
        }
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
