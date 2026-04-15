package javaxx.newfeatures.virtualthreads;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThreadDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=== VirtualThreadDemo ===");

        launchVirtualThreadDirectly();
        launchVirtualThreadsWithExecutor();
        benchmarkVirtualThreads();
    }

    private static void launchVirtualThreadDirectly() throws InterruptedException {
        System.out.println("\n-- Direct virtual thread example --");

        Thread vt = Thread.startVirtualThread(() -> {
            System.out.println("Hello from virtual thread: " + Thread.currentThread().getName());
        });

        vt.join();
        System.out.println("Direct virtual thread has completed.");
    }

    private static void launchVirtualThreadsWithExecutor() throws InterruptedException {
        System.out.println("\n-- Virtual thread executor example --");

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Runnable> tasks = List.of(
                () -> sleepAndPrint(1, 150),
                () -> sleepAndPrint(2, 100),
                () -> sleepAndPrint(3, 50)
            );

            tasks.forEach(executor::submit);
        }

        System.out.println("All virtual-thread executor tasks submitted.");
    }

    private static void benchmarkVirtualThreads() throws InterruptedException {
        System.out.println("\n-- Virtual thread throughput benchmark --");

        final int taskCount = 200;
        Instant start = Instant.now();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                final int id = i;
                executor.submit(() -> {
                    sleepAndPrint(id, 10);
                });
            }
        }

        Instant end = Instant.now();
        Duration elapsed = Duration.between(start, end);
        System.out.println("Completed " + taskCount + " virtual thread tasks in " + elapsed.toMillis() + " ms.");
    }

    private static void sleepAndPrint(int id, long millis) {
        try {
            Thread.sleep(millis);
            System.out.println("Task " + id + " completed on " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Task " + id + " was interrupted.");
        }
    }
}
