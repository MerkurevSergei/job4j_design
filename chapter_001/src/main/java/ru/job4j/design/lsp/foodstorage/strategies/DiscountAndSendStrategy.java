package ru.job4j.design.lsp.foodstorage.strategies;

import ru.job4j.design.lsp.foodstorage.food.Food;
import ru.job4j.design.lsp.foodstorage.storage.Storage;

public class DiscountAndSendStrategy implements Strategy {
    private Storage storage;

    public DiscountAndSendStrategy(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void distribute(Food food) {
        food.setDiscount(10);
        storage.add(food);
    }
}
