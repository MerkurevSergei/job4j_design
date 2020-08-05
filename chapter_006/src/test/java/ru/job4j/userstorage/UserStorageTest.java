package ru.job4j.userstorage;


import org.junit.Test;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStorageTest {

    @Test
    public void testAdd() throws InterruptedException {
        final UserStorage userStorage = new UserStorage();
        final AtomicInteger generator = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                final int id = new Random().nextInt(10);
                final int amount = new Random().nextInt();
                if (userStorage.add(new User(id, amount))) {
                    generator.incrementAndGet();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        assertEquals(generator.get(), userStorage.size());
    }

    @Test
    public void testTransfer() throws InterruptedException {
        final UserStorage userStorage = new UserStorage();
        final User user1 = new User(1, 1000);
        final User user2 = new User(2, 1000);
        userStorage.add(user1);
        userStorage.add(user2);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> userStorage.transfer(1, 2, new Random().nextInt(10)));
        }
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        assertEquals(2000, user1.getAmount() + user2.getAmount());
    }
}