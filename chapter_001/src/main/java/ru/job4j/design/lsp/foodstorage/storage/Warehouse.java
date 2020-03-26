package ru.job4j.design.lsp.foodstorage.storage;

import ru.job4j.design.lsp.foodstorage.food.Food;

import java.util.Calendar;

public class Warehouse extends AbstractStorage implements Storage {
    @Override
    public boolean check(Food food, Calendar currentDate) {
        double reminder = expireReminder(food, currentDate);
        return reminder > 0.75;
    }
}
