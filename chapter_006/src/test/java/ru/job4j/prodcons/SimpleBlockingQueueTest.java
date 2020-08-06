package ru.job4j.prodcons;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void produceAndConsume() throws InterruptedException {
        final SimpleBlockingQueue<Integer> q = new SimpleBlockingQueue<>();
        final ArrayList<Integer> actual = new ArrayList<>();
        final ArrayList<Integer> result = new ArrayList<>();
        final Thread producer = new Thread(() -> {
                int val = new Random().nextInt();
                q.offer(val);
                actual.add(val);
        });
        final Thread consumer = new Thread(() -> {
            try {
                result.add(q.poll());
            } catch (InterruptedException ignored) { }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        assertArrayEquals(result.toArray(), actual.toArray());
    }
}