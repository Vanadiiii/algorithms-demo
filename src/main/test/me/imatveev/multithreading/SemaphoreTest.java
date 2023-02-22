package me.imatveev.multithreading;

import org.junit.jupiter.api.Test;

import java.util.Random;

class SemaphoreTest {

    @Test
    void test() {
        final int threadCount = 20;
        final int acceptedThreadCount = 4;

        final Semaphore semaphore = new Semaphore(acceptedThreadCount);

        for (int i = 0; i < threadCount; ++i) {
            final Thread thread = new TestThread(semaphore);

            thread.start();
        }
    }

    private static class TestThread extends Thread {
        private final Semaphore semaphore;

        TestThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            semaphore.acquire();

            final int currentCount = semaphore.getCurrentCount();
            if (currentCount > semaphore.getMaxCount()) {
                throw new AssertionError("Current count - " + currentCount);
            }

            try {
                synchronized (this) {
                    sleep(new Random().nextInt(10));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            semaphore.release();
        }
    }
}