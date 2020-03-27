package ru.job4j.design.lsp.parkingcontrol;

/**
 * Track implement vehicle interface.
 */
public class Track implements Vehicle {
    /**
     * Require spots for parking.
     */
    int size;

    /**
     * @param size - require spots for parking
     */
    public Track(int size) {
        this.size = size;
    }

    /**
     * @return require spots for parking
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * @return true if car is track
     */
    @Override
    public boolean isTrack() {
        return true;
    }
}
