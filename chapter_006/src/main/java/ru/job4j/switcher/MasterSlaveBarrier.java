package ru.job4j.switcher;

public class MasterSlaveBarrier {
    private boolean masterLock = true;
    private boolean slaveLock = false;

    public synchronized void tryMaster() throws InterruptedException {
        if (slaveLock) {
            wait();
        }
        masterLock = true;
    }

    public synchronized void trySlave() throws InterruptedException {
        if (masterLock) {
            wait();
        }
        slaveLock = true;
    }

    public synchronized void doneMaster() throws InterruptedException {
        masterLock = false;
        notifyAll();
        wait();
    }

    public synchronized void doneSlave() throws InterruptedException {
        slaveLock = false;
        notifyAll();
        wait();
    }
}
