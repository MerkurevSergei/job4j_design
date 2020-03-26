package ru.job4j.design.lsp.parkingcontrol;

public interface Parking {
    void add(Vehicle vehicle);
    void check(Vehicle vehicle);
}
