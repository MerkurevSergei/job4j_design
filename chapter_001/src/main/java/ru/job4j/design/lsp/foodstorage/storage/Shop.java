package ru.job4j.design.lsp.foodstorage.storage;

import ru.job4j.design.lsp.foodstorage.food.Food;

import java.util.Calendar;

public class Shop extends AbstractStorage implements Storage {
    @Override
    public boolean check(Food food, Calendar currentDate) {
        double reminder = expireReminder(food, currentDate);
        if (0.00 <  reminder && reminder <=  0.25) {
            food.setDiscount(true);
        }
        return 0.00 <  reminder && reminder <=  0.75;
    }
}
