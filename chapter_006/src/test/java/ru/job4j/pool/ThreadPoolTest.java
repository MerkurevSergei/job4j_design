package ru.job4j.pool;



import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class ThreadPoolTest {

    @Test(threadPoolSize = 3, invocationCount = 30, timeOut = 1000)
    public void work() throws InterruptedException {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 20; i++) {
            threadPool.work(atomicInteger::incrementAndGet);
        }
        threadPool.shutdown();
        assertEquals(20, atomicInteger.get());
    }
}