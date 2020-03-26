package ru.job4j.design.lsp.foodstorage.strategies;

import ru.job4j.design.lsp.foodstorage.food.Food;
import ru.job4j.design.lsp.foodstorage.storage.Storage;

public class SendStrategy implements Strategy {
    private Storage storage;

    public SendStrategy(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void distribute(Food food) {
        storage.add(food);
    }
}
