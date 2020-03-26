package ru.job4j.design.lsp.foodstorage.storage;

public class StorageFactory {
    private Storage warehouse = new Warehouse();
    private Storage trash = new Trash();
    private Storage shop = new Shop();

    public Storage getWarehouse() {
        return warehouse;
    }

    public Storage getTrash() {
        return trash;
    }

    public Storage getShop() {
        return shop;
    }
}
