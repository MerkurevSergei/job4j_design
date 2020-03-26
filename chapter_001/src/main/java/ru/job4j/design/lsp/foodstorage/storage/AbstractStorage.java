package ru.job4j.design.lsp.foodstorage.storage;

import ru.job4j.design.lsp.foodstorage.food.Food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

abstract public class AbstractStorage implements Storage {
    private List<Food> foodStorage = new ArrayList<>();

    public double expireReminder(Food food, Calendar currentDate) {
        double shelfLife = food.getExpireDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        double elapsedLife = food.getExpireDate().getTimeInMillis() - currentDate.getTimeInMillis();
        return elapsedLife / shelfLife;
    }

    @Override
    public void add(Food food) {
        foodStorage.add(food);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractStorage that = (AbstractStorage) o;
        return Objects.equals(foodStorage, that.foodStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodStorage);
    }
}
