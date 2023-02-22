package me.imatveev.multithreading;

public class Semaphore {
    private volatile int currentCount;
    private final int maxCount;

    public Semaphore(int maxCount) {
        this.currentCount = 0;
        this.maxCount = maxCount;
    }

    public int getMaxCount() {
        return this.maxCount;
    }

    public int getCurrentCount() {
        return this.currentCount;
    }

    public synchronized void acquire() {
        while (currentCount >= maxCount) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ++currentCount;
    }

    public synchronized void release() {
        --currentCount;
        notifyAll();
    }
}
