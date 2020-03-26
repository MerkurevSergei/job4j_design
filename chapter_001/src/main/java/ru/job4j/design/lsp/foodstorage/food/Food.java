package ru.job4j.design.lsp.foodstorage.food;

import java.util.Calendar;

public class Food {
    private String name;
    private Calendar createDate;
    private Calendar expireDate;
    private double price;
    private double discount;

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
