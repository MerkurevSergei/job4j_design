package ru.job4j.design.lsp.parkingcontrol;

import org.junit.Before;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParkingManagerTest {

    @Test
    public void executeSuccess() throws Exception {
        // Vehicles for distribute
        Car car = new Car();
        Track track2 = new Track(2);
        Track track3 = new Track(3);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        vehicles.add(track2);
        vehicles.add(track3);

        // Setting the expected parkings state
        ParkingTrack expParkingTrack = new ParkingTrack(3);
        expParkingTrack.add(track2);

        ParkingCar expParkingCar = new ParkingCar(5);
        expParkingCar.add(car);
        expParkingCar.add(track3);

        List<Parking> expectedParkings = new ArrayList<>();
        expectedParkings.add(expParkingTrack);
        expectedParkings.add(expParkingCar);

        // Out result after distribute
        List<Parking> outParkings = new ArrayList<>();
        outParkings.add(new ParkingTrack(3));
        outParkings.add(new ParkingCar(5));
        ParkingManager manager = new ParkingManager(outParkings);
        manager.execute(vehicles);

        assertThat(expectedParkings, is(outParkings));
    }

    @Test(expected = Exception.class)
    public void executeFailed() throws Exception {
        // Vehicles for distribute
        List<Vehicle> vehicles = new ArrayList<>();
        Track track2 = new Track(2);
        vehicles.add(track2);

        // Setting the expected parkings state
        List<Parking> expectedParkings = new ArrayList<>();

        // Out result after distribute
        List<Parking> outParkings = new ArrayList<>();
        outParkings.add(new ParkingTrack(1));
        ParkingManager manager = new ParkingManager(outParkings);
        manager.execute(vehicles);

        assertThat(expectedParkings, is(outParkings));
    }

}