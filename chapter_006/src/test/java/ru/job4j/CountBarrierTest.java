package ru.job4j;

import org.junit.Test;
import ru.job4j.concurrent.ThreadState;

import static org.junit.Assert.*;

public class CountBarrierTest {

    @Test
    public void startWaitAndAwake() throws InterruptedException {
        CountBarrier barrier = new CountBarrier(10);
        Thread master = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        barrier.count();
                    }
                },
                "Master"
        );
        Thread slave = new Thread(
                barrier::await,
                "Slave"
        );
        slave.start();
        master.start();
        master.join();
        slave.join();
        assertSame(slave.getState(), Thread.State.TERMINATED);
    }
}
