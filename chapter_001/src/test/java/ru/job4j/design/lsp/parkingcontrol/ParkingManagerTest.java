package ru.job4j.design.lsp.parkingcontrol;

import org.junit.Before;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParkingManagerTest {
    List<Parking> parkings = new ArrayList<>();
    List<Vehicle> vehicles = new ArrayList<>();
    @Before
    public void setUp() {
        parkings.add(new ParkingTrack(10));
        parkings.add(new ParkingCar(10));

        vehicles.add(new Track(6));
        vehicles.add(new Car());
        vehicles.add(new Car());
        vehicles.add(new Car());
        vehicles.add(new Track(5));
        vehicles.add(new Car());
        vehicles.add(new Car());

    }

    @Test
    public void executeSuccess() {
        ParkingTrack expectedParkingTrack = new ParkingTrack(10);
        expectedParkingTrack.add(vehicles.get(0));
        ParkingCar expectedParkingCar = new ParkingCar(10);
        expectedParkingCar.add(vehicles.get(1));
        expectedParkingCar.add(vehicles.get(2));
        expectedParkingCar.add(vehicles.get(3));
        expectedParkingCar.add(vehicles.get(4));
        expectedParkingCar.add(vehicles.get(5));
        expectedParkingCar.add(vehicles.get(6));

        List<Parking> expectedParkings = new ArrayList<>();
        expectedParkings.add(expectedParkingTrack);
        expectedParkings.add(expectedParkingCar);

        ParkingManager manager = new ParkingManager(parkings);
        manager.execute(vehicles);

        assertThat(expectedParkings, is(parkings));
    }

    @Test(expected = Exception.class)
    public void executeFailed() {
        vehicles.add(new Car());

        ParkingTrack expectedParkingTrack = new ParkingTrack(10);
        expectedParkingTrack.add(vehicles.get(0));
        ParkingCar expectedParkingCar = new ParkingCar(10);
        expectedParkingCar.add(vehicles.get(1));
        expectedParkingCar.add(vehicles.get(2));
        expectedParkingCar.add(vehicles.get(3));
        expectedParkingCar.add(vehicles.get(4));
        expectedParkingCar.add(vehicles.get(5));
        expectedParkingCar.add(vehicles.get(6));

        List<Parking> expectedParkings = new ArrayList<>();
        expectedParkings.add(expectedParkingTrack);
        expectedParkings.add(expectedParkingCar);

        ParkingManager manager = new ParkingManager(parkings);
        manager.execute(vehicles);

        assertThat(expectedParkings, is(parkings));
    }


}