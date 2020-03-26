package ru.job4j.design.lsp.foodstorage;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.foodstorage.food.Alcohol;
import ru.job4j.design.lsp.foodstorage.food.Bakery;
import ru.job4j.design.lsp.foodstorage.food.Food;
import ru.job4j.design.lsp.foodstorage.storage.Shop;
import ru.job4j.design.lsp.foodstorage.storage.Storage;
import ru.job4j.design.lsp.foodstorage.storage.Trash;
import ru.job4j.design.lsp.foodstorage.storage.Warehouse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    ArrayList<Storage> storages = new ArrayList<>();
    ArrayList<Food> foods = new ArrayList<>();

    @Before
    public void setUp() {
        storages.add(new Warehouse());
        storages.add(new Shop());
        storages.add(new Trash());

        foods.add(new Food("Kirieshki salt",
                new GregorianCalendar(2020, Calendar.JANUARY, 1),
                new GregorianCalendar(2030, Calendar.JANUARY, 1),
                1,
                false));
        foods.add(new Food("Best meet", new GregorianCalendar(2020, Calendar.JANUARY, 1),
                new GregorianCalendar(2021, Calendar.DECEMBER, 31),
                1,
                false));
        foods.add(new Alcohol("Vodka",
                new GregorianCalendar(2020, Calendar.FEBRUARY, 1),
                new GregorianCalendar(2020, Calendar.DECEMBER, 30),
                1,
                false));
        foods.add(new Food("Cheese",
                new GregorianCalendar(2020, Calendar.MAY, 1),
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 1),
                1,
                false));
        foods.add(new Bakery("Bread",
                new GregorianCalendar(2020, Calendar.JUNE, 1),
                new GregorianCalendar(2020, Calendar.JULY, 10),
                1,
                false));
        foods.add(new Bakery("Bread 2",
                new GregorianCalendar(2020, Calendar.JUNE, 1),
                new GregorianCalendar(2020, Calendar.JUNE, 20),
                1,
                false));
    }

    @Test
    public void whenDistributeSuccess() {
        ArrayList<Storage> expectedStorages = new ArrayList<>();
        expectedStorages.add(new Warehouse());
        expectedStorages.add(new Shop());
        expectedStorages.add(new Trash());
        expectedStorages.get(0).add(foods.get(0));
        expectedStorages.get(0).add(foods.get(1));
        expectedStorages.get(1).add(foods.get(2));
        expectedStorages.get(1).add(foods.get(3));
        expectedStorages.get(1).add(foods.get(4));
        expectedStorages.get(2).add(foods.get(5));

        ControlQuality control = new ControlQuality(storages, new GregorianCalendar(2020, Calendar.JULY, 1));
        control.distribute(foods);

        assertThat(storages, is(expectedStorages));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDistributeNotSuccess() {
        foods.add(new Food("Kirieshki salt",
                new GregorianCalendar(2020, Calendar.JANUARY, 1),
                new GregorianCalendar(2019, Calendar.JANUARY, 1),
                1,
                false));

        ArrayList<Storage> expectedStorages = new ArrayList<>();
        expectedStorages.add(new Warehouse());
        expectedStorages.add(new Shop());
        expectedStorages.add(new Trash());
        expectedStorages.get(0).add(foods.get(0));
        expectedStorages.get(0).add(foods.get(1));
        expectedStorages.get(1).add(foods.get(2));
        expectedStorages.get(1).add(foods.get(3));
        expectedStorages.get(1).add(foods.get(4));
        expectedStorages.get(2).add(foods.get(5));

        ControlQuality control = new ControlQuality(storages, new GregorianCalendar(2020, Calendar.JULY, 1));
        control.distribute(foods);

        assertThat(storages, is(expectedStorages));
    }
}