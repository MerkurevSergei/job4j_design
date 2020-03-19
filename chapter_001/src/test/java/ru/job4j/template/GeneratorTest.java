package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class GeneratorTest {

    @Test
    public void produce() {
        Generator generator = (template, args) -> template;
        assertThat("ExpectedTemplate", is(generator.produce("ExpectedTemplate", new HashMap<>())));
    }
}