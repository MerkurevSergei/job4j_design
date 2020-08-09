package ru.job4j.switcher;

public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        final MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread first = new Thread(
                () -> {
                    while (true) {
                        try {
                            barrier.tryMaster();
                            System.out.println("Thread A");
                            Thread.sleep(1000);
                            barrier.doneMaster();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        try {
                            barrier.trySlave();
                            System.out.println("Thread B");
                            Thread.sleep(1000);
                            barrier.doneSlave();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
