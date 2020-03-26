package ru.job4j.design.lsp.foodstorage.storage;

import ru.job4j.design.lsp.foodstorage.food.Food;

import java.util.Calendar;

/**
 * Storage interface
 */
public interface Storage {
    /**
     * @param food        - check the food
     * @param currentDate - current date
     * @return can be stored
     */
    boolean check(Food food, Calendar currentDate);

    /**
     * @param food - added food
     */
    void add(Food food);
}
