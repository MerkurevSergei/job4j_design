package ru.job4j.design.lsp.foodstorage.strategies;

import ru.job4j.design.lsp.foodstorage.food.Food;
import ru.job4j.design.lsp.foodstorage.storage.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StrategyFactory {
    Map<Predicate<Double>, Strategy> strategyMap = new HashMap<>();

    public StrategyFactory(StorageFactory storageFactory) {
        strategyMap.put((expiryReminder -> expiryReminder <= 0.00), new SendStrategy(storageFactory.getTrash()));
        strategyMap.put((expiryReminder -> 0.00 <  expiryReminder && expiryReminder <  0.25), new DiscountAndSendStrategy(storageFactory.getShop()));
        strategyMap.put((expiryReminder -> 0.25 <= expiryReminder && expiryReminder <= 0.75), new SendStrategy(storageFactory.getShop()));
        strategyMap.put((expiryReminder -> 0.75 <  expiryReminder), new SendStrategy(storageFactory.getWarehouse()));
    }

    public Strategy getStrategy(Double expiryReminder) {
        Strategy strategy = new SendStrategy(new Warehouse());
        for (Map.Entry<Predicate<Double>, Strategy> e : strategyMap.entrySet()) {
            if (e.getKey().test(expiryReminder)) {
                strategy = e.getValue();
                break;
            }
        }
        return strategy;
    }

    
}
