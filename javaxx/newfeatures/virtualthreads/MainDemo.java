package javaxx.newfeatures.virtualthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainDemo {
    public static void main(String[] args) throws InterruptedException {
       // usingThreadClass();
        usingExecutorService();
    }

    private static void usingThreadClass() throws InterruptedException {
        Runnable task = () -> {
            System.out.println("Hello from virtual thread: " + Thread.currentThread().getName());
        };

        Thread virtualThread = Thread.ofVirtual().start(task);
        virtualThread.join();
    }

    private static void usingExecutorService() {
        // Example method to illustrate virtual threads
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 1000; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running on thread: " + Thread.currentThread().getName());
                // Simulate some work
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e.fillInStackTrace().getLocalizedMessage());
                }
            });
        }

      //  executor.shutdown();
    }

}
