package me.imatveev.multithreading;

public class DoubleLockCheckingLazySingleton {
    private static volatile DoubleLockCheckingLazySingleton instance;
    private final int someValue;

    private DoubleLockCheckingLazySingleton() {
        this.someValue = 42;
    }

    public static DoubleLockCheckingLazySingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleLockCheckingLazySingleton.class) {
                if (instance == null) {
                    instance = new DoubleLockCheckingLazySingleton();
                }
            }
        }
        return instance;
    }

    public int getSomeValue() {
        return this.someValue;
    }
}
