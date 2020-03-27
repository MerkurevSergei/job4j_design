package ru.job4j.design.lsp.parkingcontrol;

/**
 * Passenger car.
 */
public class Car implements Vehicle {
    /**
     * @return require spots for parking
     */
    @Override
    public int getSize() {
        return 1;
    }

    /**
     * @return true if car is track
     */
    @Override
    public boolean isTrack() {
        return false;
    }
}
