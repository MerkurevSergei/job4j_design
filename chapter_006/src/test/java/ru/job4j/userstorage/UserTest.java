package ru.job4j.userstorage;

import org.junit.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;

public class UserTest {

    @Test
    public void testGetId() {
        final User user = new User(1, 0);
        assertEquals(1, user.getId());
    }

    @Test
    public void testSetAndGetAmount() {
        final User user = new User(1, 0);
        final int amount = new Random().nextInt();
        user.setAmount(amount);
        assertEquals(user.getAmount(), amount);
    }
}