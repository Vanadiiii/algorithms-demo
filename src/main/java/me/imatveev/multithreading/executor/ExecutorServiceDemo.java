package me.imatveev.multithreading.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        final Callable<Integer> callable = () -> {
            System.out.println("Hello, from Callable");
            TimeUnit.SECONDS.sleep(3L);
            return 42;
        };

        final Future<Integer> futureResult = executorService.submit(callable);

        final Integer result;
        try {
            result = futureResult.get(1L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        System.out.println("result - " + result);
    }
}
