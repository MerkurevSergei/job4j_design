package ru.job4j.design.lsp.parkingcontrol;

/**
 * Parking interface.
 */
public interface Parking {
    /**
     * Add vehicle to parking.
     *
     * @param vehicle - vehicle
     */
    void add(Vehicle vehicle);

    /**
     * Check vehicle can add to parking
     *
     * @param vehicle - checking car
     * @return check result
     */
    boolean check(Vehicle vehicle);
}
