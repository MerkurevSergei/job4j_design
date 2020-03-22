package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class GeneratorTest {

    @Test
    public void produceWhenArgsNormal() {
        Generator generator = (template, args) -> template;
        assertThat("ExpectedTemplate", is(generator.produce("ExpectedTemplate", new HashMap<>())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceWhenNotEnoughOrAnotherArgsThenInTemplate() {
        Generator generator = (template, args) -> {
            throw new IllegalArgumentException("В шаблоне существуют ключи, которых нет в карте!");
        };
        assertThat("NotExpectedTemplate", is(generator.produce("AnotherTemplate", new HashMap<>())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceWhenMoreArgsThenInTemplate() {
        Generator generator = (template, args) -> {
            throw new IllegalArgumentException("В карте есть ключи, которых нет в шаблоне!");
        };

        assertThat("NotExpectedTemplate", is(generator.produce("AnotherTemplate", new HashMap<>())));
    }
}