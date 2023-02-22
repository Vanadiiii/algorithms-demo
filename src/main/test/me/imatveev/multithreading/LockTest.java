package me.imatveev.multithreading;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LockTest {
    private static final int threadCounts = 20;

    @Test
    @Timeout(threadCounts / 4)
    void test01() {
        final Lock lock = new Lock();
        final ThreadUnsafeCounter threadUnsafeCounter = new ThreadUnsafeCounter(0);
        final AtomicInteger threadSafeCounter = new AtomicInteger(0);

        for (int i = 0; i < threadCounts; ++i) {
            final TestThread thread = new TestThread(lock, threadUnsafeCounter, threadSafeCounter);
            thread.start();
        }

        while (threadSafeCounter.get() != threadCounts) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        assertEquals(threadCounts, threadUnsafeCounter.get());
    }

    public static class TestThread extends Thread {
        private final Lock lock;
        private final ThreadUnsafeCounter unsafeCounter;
        private final AtomicInteger safeCounter;


        public TestThread(Lock lock, ThreadUnsafeCounter unsafeCounter, AtomicInteger safeCounter) {
            this.lock = lock;
            this.unsafeCounter = unsafeCounter;
            this.safeCounter = safeCounter;
        }

        @Override
        public void run() {
            lock.lock();

            try {
                synchronized (this) {
                    sleep(new Random().nextInt(100));
                }
            } catch (InterruptedException e) {
                throw new AssertionError(e);
            }

            unsafeCounter.incrementAndGet();

            lock.unlock();

            safeCounter.incrementAndGet();
        }
    }

    public static class ThreadUnsafeCounter {
        private int count;

        public ThreadUnsafeCounter(int initial) {
            this.count = initial;
        }

        public int incrementAndGet() {
            return ++count;
        }

        public int get() {
            return count;
        }
    }
}