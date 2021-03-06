package ru.job4j.design.lsp.parkingcontrol;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Parking track implements parking interface.
 */
public class ParkingTrack implements Parking {
    /**
     * list of vehicles
     */
    List<Vehicle> vehicles = new ArrayList<>();

    /**
     * parking size
     */
    int spots;

    /**
     * @param spots - parking size
     */
    public ParkingTrack(int spots) {
        this.spots = spots;
    }

    /**
     * @param vehicle - added vehicle
     */
    @Override
    public void add(Vehicle vehicle) {
        vehicles.add(vehicle);
        spots -= vehicle.getSize();
    }

    /**
     * @param vehicle - checking car
     * @return - check result
     */
    @Override
    public boolean check(Vehicle vehicle) {
        return vehicle.isTrack() && spots >= vehicle.getSize();
    }

    /**
     * @param o - object
     * @return result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingTrack that = (ParkingTrack) o;
        return spots == that.spots && Objects.equals(vehicles, that.vehicles);
    }

    /**
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(vehicles, spots);
    }
}
