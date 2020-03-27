package ru.job4j.design.lsp.parkingcontrol;

import java.util.List;

/**
 * Parking manager.
 * Controls placement vehicles to parking
 */
public class ParkingManager {
    /**
     * Parkings list
     */
    List<Parking> parkings;

    /**
     * @param parkings - parkings parameters
     */
    public ParkingManager(List<Parking> parkings) {
        this.parkings = parkings;
    }

    /**
     * execute placement vehicles to parking
     * @param vehicles - list of vehicles
     * @throws Exception - if can't placement
     */
    public void execute(List<Vehicle> vehicles) throws Exception {
        for (Vehicle v : vehicles) {
            boolean posted = false;
            for (Parking p : parkings) {
                if (p.check(v)) {
                    p.add(v);
                    posted = true;
                    break;
                }
            }
            if (!posted) {
                throw new Exception("Нет мест!");
            }
        }
    }
}
