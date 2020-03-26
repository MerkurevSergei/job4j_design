package ru.job4j.design.lsp.foodstorage.food;

import java.util.Calendar;
import java.util.Objects;

/**
 * Model of food.
 */
public class Food {
    /**
     * Food name
     */
    private String name;
    /**
     * Food create date
     */
    private Calendar createDate;
    /**
     * Food expire date
     */
    private Calendar expireDate;
    /**
     * Food price
     */
    private double price;
    /**
     * Food discount
     */
    private boolean discount;

    /**
     * @param name       - food name value
     * @param createDate - create date value
     * @param expireDate - expire date value
     * @param price      - price value
     * @param discount   - discount value
     */
    public Food(String name, Calendar createDate, Calendar expireDate, double price, boolean discount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = discount;
        if (createDate.compareTo(expireDate) > 0) {
            throw new IllegalArgumentException("create date more then expire date");
        }
    }

    /**
     * Food name getter.
     *
     * @return food name
     */
    public String getName() {
        return name;
    }

    /**
     * Create date getter.
     *
     * @return food create date
     */
    public Calendar getCreateDate() {
        return createDate;
    }

    /**
     * Expire date getter.
     *
     * @return food expire date
     */
    public Calendar getExpireDate() {
        return expireDate;
    }

    /**
     * Food price getter.
     *
     * @return food price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Food discount getter.
     *
     * @return food discount
     */
    public boolean getDiscount() {
        return discount;
    }

    /**
     * Food discount setter.
     *
     * @param discount - food discount
     */
    public void setDiscount(boolean discount) {
        this.discount = discount;
    }
}
