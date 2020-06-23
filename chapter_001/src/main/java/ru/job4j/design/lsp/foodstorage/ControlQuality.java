package ru.job4j.design.lsp.foodstorage;

import ru.job4j.design.lsp.foodstorage.food.Food;
import ru.job4j.design.lsp.foodstorage.storage.Storage;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Foods distributor to storage.
 */
public class ControlQuality {

    /**
     * Food abstract storages.
     */
    private final List<Storage> storages;

    /**
     * Date of expiration check.
     */
    private Calendar currentDate;

    /**
     * @param storages    - init value from storages
     * @param currentDate - init value from current date
     */
    public ControlQuality(List<Storage> storages, Calendar currentDate) {
        this.storages = storages;
        this.currentDate = currentDate;
    }

    /**
     * Set current date
     * @param currentDate init
     */
    public void setCurrentDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * Distribute foods to storages
     *
     * @param foods - list of food
     */
    public void distribute(List<Food> foods) {
        for (Food food : foods) {
            for (Storage storage : storages) {
                if (storage.check(food, currentDate)) {
                    storage.add(food);
                    break;
                }
            }
        }
    }

    /**
     * Resort foods from storages
     */
    public void resort() {
        List<Food> tFood = new LinkedList<>();
        for (Storage storage: storages) {
            tFood.addAll(storage.getAll());
            storage.clear();
        }
        distribute(tFood);
    }
}
