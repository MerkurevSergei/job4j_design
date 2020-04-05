package ru.job4j.io;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenNumberFileTest {

    @Test
    public void execute() {
        List<EvenNumberFile.Pair> expected = new ArrayList<>();
        expected.add(new EvenNumberFile.Pair(1, false));
        expected.add(new EvenNumberFile.Pair(5, false));
        expected.add(new EvenNumberFile.Pair(18, true));
        List<EvenNumberFile.Pair> out = new EvenNumberFile().execute();
        assertThat(expected, is(out));
    }
}