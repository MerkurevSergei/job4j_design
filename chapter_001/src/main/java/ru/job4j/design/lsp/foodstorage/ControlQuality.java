package ru.job4j.design.lsp.foodstorage;

import ru.job4j.design.lsp.foodstorage.strategies.Strategy;
import ru.job4j.design.lsp.foodstorage.food.Food;
import ru.job4j.design.lsp.foodstorage.storage.Storage;
import ru.job4j.design.lsp.foodstorage.strategies.StrategyFactory;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControlQuality {

    private StrategyFactory strategyFactory;
    private Calendar currentDate;
    private List<Food> foods;

    public ControlQuality(StrategyFactory strategyFactory, Calendar currentDate, List<Food> foods) {
        this.strategyFactory = strategyFactory;
        this.currentDate = currentDate;
        this.foods = foods;
    }

    public void distribute(Calendar currentDate) {
        for (Food food : foods) {
            Double remainder = remainderCalculate(food, currentDate);

            // 0. ControlQuality - клиент? Или клиент StrategyFactory?
            Strategy strategy = strategyFactory.getStrategy(remainder);

            // 1. Вот так везде описывается стратегия
            // Но зачем тут Context, ведь вся информация у нас есть в ControlQuality?
            new Context().setStrategy(strategy).execute(food);

            // 2. В этом случае ControlQuality выполняет роль Context
            setStrategy(strategy);
            excecute(food);

            // 3. Но поскольку Strategy - интерфейс, то возможно допустимо вместо 2. и так? Без лишних методов
            strategy.distribute(food);
        }
        foods.clear();
    }

    private double remainderCalculate(Food food, Calendar currentDate) {
        return 0.0;
    }

}
