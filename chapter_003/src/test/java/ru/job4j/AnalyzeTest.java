package ru.job4j;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AnalyzeTest {

    @Test
    public void diff() {
        final List<Analyze.User> previous = List.of(new Analyze.User(1, "1"),
                new Analyze.User(2, "2"),
                new Analyze.User(3, "3"),
                new Analyze.User(4, "4"));
        final List<Analyze.User> current = List.of(new Analyze.User(1, "1"),
                new Analyze.User(2, "02"),
                new Analyze.User(5, "5"),
                new Analyze.User(6, "6"),
                new Analyze.User(7, "7"));
        final Analyze.Info info = new Analyze().diff(previous, current);
        assertEquals(1, info.getChanged());
        assertEquals(2, info.getDeleted());
        assertEquals(3, info.getAdded());
    }
}