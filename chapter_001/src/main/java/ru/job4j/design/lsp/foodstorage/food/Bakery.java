package ru.job4j.design.lsp.foodstorage.food;

import java.util.Calendar;

/**
 * Food type - bakery
 */
public class Bakery extends Food {
    /**
     * @param name       - food name value
     * @param createDate - create date value
     * @param expireDate - expire date value
     * @param price      - price value
     * @param discount   - discount value
     */
    public Bakery(String name, Calendar createDate, Calendar expireDate, double price, boolean discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
