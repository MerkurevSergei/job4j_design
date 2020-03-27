package ru.job4j.design.lsp.parkingcontrol;

public class Track implements Vehicle {
    int size;

    public Track(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isTrack() {
        return false;
    }
}
