package javaxx.newfeatures.memoryimprovements;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

/**
 * Demonstrates memory-related improvements and runtime inspection points
 * introduced between Java 8 and Java 21.
 *
 * Key platform improvements include:
 * - Java 9: Compact Strings, G1 as default GC, improved string deduplication
 * - Java 10: Application Class-Data Sharing (AppCDS)
 * - Java 11: ZGC preview / Flight Recorder open source
 * - Java 12: Shenandoah GC early access
 * - Java 15: ZGC and Shenandoah become production-ready
 * - Java 21: Foreign Function & Memory API reaches maturity
 */
public class MemoryImprovementsDemo {
    public static void main(String[] args) {
        System.out.println("=== Memory Improvements Demo ===");
        printPlatformMemorySummary();
        printGcBeans();
        printMemoryPools();
        printJava21MemoryNote();
    }

    private static void printPlatformMemorySummary() {
        Runtime runtime = Runtime.getRuntime();
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();

        System.out.println("\n-- Runtime memory summary --");
        System.out.println("Max memory: " + formatBytes(max));
        System.out.println("Total memory: " + formatBytes(total));
        System.out.println("Free memory: " + formatBytes(free));
        System.out.println("Used memory: " + formatBytes(total - free));
        System.out.println("Available processors: " + runtime.availableProcessors());
    }

    private static void printGcBeans() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();

        System.out.println("\n-- Memory MXBean summary --");
        System.out.println("Heap memory usage: " + formatUsage(memoryBean.getHeapMemoryUsage()));
        System.out.println("Non-heap memory usage: " + formatUsage(memoryBean.getNonHeapMemoryUsage()));

        System.out.println("\n-- Garbage collector beans --");
        gcBeans.forEach(bean -> System.out.println("GC: " + bean.getName() + " (" + String.join(", ", bean.getMemoryPoolNames()) + ")"));
    }

    private static void printMemoryPools() {
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();

        System.out.println("\n-- Memory pools --");
        for (MemoryPoolMXBean pool : pools) {
            System.out.println(pool.getName() + ": " + pool.getType() + " " + formatUsage(pool.getUsage()));
        }
    }

    private static void printJava21MemoryNote() {
        System.out.println("\n-- Java memory improvement notes --");
        System.out.println("Java 9 introduced Compact Strings and made G1 the default garbage collector.");
        System.out.println("Java 10 improved startup and footprint with Application Class-Data Sharing (AppCDS).");
        System.out.println("Java 11 opened Flight Recorder and made ZGC production-ready in later releases.");
        System.out.println("Java 15 finalized several low-latency garbage collectors, including ZGC and Shenandoah.");
        System.out.println("Java 21 matured the Foreign Function & Memory API for safer off-heap memory access.");
    }

    private static String formatUsage(MemoryUsage usage) {
        return "init=" + formatBytes(usage.getInit()) + ", used=" + formatBytes(usage.getUsed()) + ", committed=" + formatBytes(usage.getCommitted()) + ", max=" + formatBytes(usage.getMax());
    }

    private static String formatBytes(long bytes) {
        if (bytes < 0) {
            return "unknown";
        }
        double value = bytes;
        String unit = "B";
        if (bytes >= 1_000_000_000) {
            value = bytes / 1_000_000_000.0;
            unit = "GB";
        } else if (bytes >= 1_000_000) {
            value = bytes / 1_000_000.0;
            unit = "MB";
        } else if (bytes >= 1_000) {
            value = bytes / 1_000.0;
            unit = "KB";
        }
        return String.format("%.2f %s", value, unit);
    }
}
