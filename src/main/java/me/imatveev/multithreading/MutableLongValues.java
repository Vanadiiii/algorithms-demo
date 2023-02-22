package me.imatveev.multithreading;

public class MutableLongValues {
    public static void main(String[] args) {
        final Counter counter = new Counter();

        for (int i = 0; i < 100; i++) {
            final Thread thread = new Mutator(counter);

            thread.start();
        }
    }

    private static class Counter {
        private long value;
    }

    private static class Mutator extends Thread {
        private final Counter counter;

        private Mutator(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            ++counter.value;
            System.out.println(this.getName() + ": read counter - " + counter.value);
        }
    }
}
