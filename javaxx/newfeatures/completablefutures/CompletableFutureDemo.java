package javaxx.newfeatures.completablefutures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        System.out.println("=== CompletableFuture Demo ===");

        synchronousExample();
        asynchronousExample();
        composedAsyncExample();
        parallelAsyncExample();
        exceptionHandlingExample();
    }

    private static void synchronousExample() {
        System.out.println("\n-- Synchronous CompletableFuture example --");

        CompletableFuture<String> future = CompletableFuture.completedFuture("Hello");

        String result = future
                .thenApply(value -> value + ", world")
                .thenApply(String::toUpperCase)
                .join();

        System.out.println("Result: " + result);
    }

    private static void asynchronousExample() {
        System.out.println("\n-- Asynchronous CompletableFuture example --");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "Async result";
        }).thenApply(value -> value + " processed");

        System.out.println("Waiting for async result...");

        try {
            String result = future.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void composedAsyncExample() {
        System.out.println("\n-- Composed async example with thenCompose() --");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(150);
            return "Step 1";
        }).thenCompose(step1 -> CompletableFuture.supplyAsync(() -> {
            sleep(150);
            return step1 + " -> Step 2";
        }));

        String result = future.join();
        System.out.println("Composed result: " + result);
    }
    private static void parallelAsyncExample() {
        System.out.println("\n-- Parallel async call example --");

        CompletableFuture<ApiResponse> responseA = CompletableFuture.supplyAsync(() -> fetchRemoteResponse("ServiceA", 130))
                .thenApply(CompletableFutureDemo::parseResponse);
        CompletableFuture<ApiResponse> responseB = CompletableFuture.supplyAsync(() -> fetchRemoteResponse("ServiceB", 90))
                .thenApply(CompletableFutureDemo::parseResponse);
        CompletableFuture<ApiResponse> responseC = CompletableFuture.supplyAsync(() -> fetchRemoteResponse("ServiceC", 110))
                .thenApply(CompletableFutureDemo::parseResponse);

        CompletableFuture<Void> allResponses = CompletableFuture
        .allOf(responseA, responseB, responseC);
        allResponses.join();

        ApiResponse a = responseA.join();
        ApiResponse b = responseB.join();
        ApiResponse c = responseC.join();

        System.out.println("Parsed responses:");
        System.out.println("  " + a);
        System.out.println("  " + b);
        System.out.println("  " + c);

        String merged = responseA.thenCombine(responseB, (left, right) -> "Merged:" + left.data() + "," + right.data())
                .join();
        System.out.println("Merged result: " + merged);
    }
    private static void exceptionHandlingExample() {
        System.out.println("\n-- Exception handling in CompletableFuture --");

        CompletableFuture<String> future = CompletableFuture.<String>supplyAsync(() -> {
            sleep(100);
            throw new IllegalStateException("Failure during computation");
            // unreachable return to satisfy compiler inference
            // return null;
        }).exceptionally(ex -> {
            System.out.println("Caught exception: " + ex.getCause());
            return "Fallback value";
        });

        String result = future.join();
        System.out.println("Final result: " + result);
    }

    private static String fetchRemoteResponse(String serviceName, int delayMillis) {
        sleep(delayMillis);
        return serviceName + ":response-for-" + serviceName.toLowerCase();
    }

    private static ApiResponse parseResponse(String rawResponse) {
        String[] parts = rawResponse.split(":", 2);
        String source = parts.length > 0 ? parts[0] : "unknown";
        String data = parts.length > 1 ? parts[1] : "";
        return new ApiResponse(source, data);
    }

    private static void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static record ApiResponse(String source, String data) {
        @Override
        public String toString() {
            return "ApiResponse{source='" + source + "', data='" + data + "'}";
        }
    }
}
