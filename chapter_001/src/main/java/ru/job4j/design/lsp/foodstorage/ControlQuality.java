package ru.job4j.design.lsp.foodstorage;

import ru.job4j.design.lsp.foodstorage.food.Food;
import ru.job4j.design.lsp.foodstorage.storage.Storage;

import java.util.Calendar;
import java.util.List;

/**
 * Foods distributor to storage.
 */
public class ControlQuality {

    /**
     * Food abstract storages.
     */
    private List<Storage> storages;

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
        foods.clear();
    }

}
