package ru.job4j.design.lsp.parkingcontrol;

/**
 * Vehicle interface
 */
public interface Vehicle {
    /**
     * @return require spots for parking
     */
    int getSize();

    /**
     * @return true if car is track
     */
    boolean isTrack();
}
