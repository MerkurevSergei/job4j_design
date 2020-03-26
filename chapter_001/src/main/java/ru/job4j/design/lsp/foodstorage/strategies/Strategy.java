package ru.job4j.design.lsp.foodstorage.strategies;

import ru.job4j.design.lsp.foodstorage.food.Food;
import ru.job4j.design.lsp.foodstorage.storage.Storage;

public interface Strategy {
    void distribute(Food food);
    void distribute(Food food, double discount);
}
