package me.imatveev.multithreading;

public class Lock {
    private boolean locked;

    public Lock() {
        this.locked = false;
    }

    public synchronized void lock() throws RuntimeException {
        while (locked) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        locked = true;
    }

    public synchronized void unlock() {
        locked = false;
        notifyAll();
    }
}
