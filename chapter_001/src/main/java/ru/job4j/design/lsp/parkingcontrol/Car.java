package ru.job4j.design.lsp.parkingcontrol;

public class Car implements Vehicle {
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isTrack() {
        return false;
    }
}
