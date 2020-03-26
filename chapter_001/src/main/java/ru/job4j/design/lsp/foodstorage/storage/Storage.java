package ru.job4j.design.lsp.foodstorage.storage;

import ru.job4j.design.lsp.foodstorage.food.Food;

public interface Storage {
    void add(Food food);
}
