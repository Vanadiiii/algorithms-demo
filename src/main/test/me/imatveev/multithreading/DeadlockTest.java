package me.imatveev.multithreading;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class DeadlockTest {
    @Test
    void test() {
        final Object resource1 = new Object();
        final Object resource2 = new Object();

        Thread thread1 = new TestThread(resource1, resource2, true);
        Thread thread2 = new TestThread(resource1, resource2, false);

        thread1.start();
        thread2.start();

        int i = 0;
        while (thread1.getState() != Thread.State.BLOCKED || thread2.getState() != Thread.State.BLOCKED) {
            System.out.println(i + ": 1 - " + thread1.getState() + "; 2 - " + thread2.getState());
        }

        assertNotEquals(Thread.State.TERMINATED, thread1.getState());
        assertSame(thread1.getState(), thread2.getState());
    }

    private static class TestThread extends Thread {
        private final Object resource1;
        private final Object resource2;
        private final boolean prime;

        private TestThread(Object resource1, Object resource2, boolean prime) {
            this.resource1 = resource1;
            this.resource2 = resource2;
            this.prime = prime;
        }

        @Override
        public void run() {
            if (prime) {
                synchronized (resource1) {
                    someWork();
                }
                synchronized (resource2) {
                    someWork();
                }
            } else {
                synchronized (resource2) {
                    someWork();
                }
                synchronized (resource1) {
                    someWork();
                }
            }
        }

        private void someWork() {
            try {
                sleep(200 + new Random().nextInt(400));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
