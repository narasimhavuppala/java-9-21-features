package javaxx.newfeatures.patternmatchingswitch;

import java.util.List;

public class PatternMatchingSwitchDemo {
    // Sealed hierarchy for demonstration
    sealed interface Vehicle permits Car, Truck, Motorcycle {}

    record Car(String make, String model, int year) implements Vehicle {}
    record Truck(String make, String model, int payloadCapacity) implements Vehicle {}
    record Motorcycle(String make, String model, boolean hasSidecar) implements Vehicle {}

    public static void main(String[] args) {
        List<Vehicle> vehicles = List.of(
            new Car("Toyota", "Camry", 2020),
            new Truck("Ford", "F-150", 2000),
            new Motorcycle("Harley-Davidson", "Sportster", false),
            new Motorcycle("BMW", "R1200GS", true)
        );

        System.out.println("Vehicle Information:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(describeVehicle(vehicle));
        }

        System.out.println("\nProcessing vehicles:");
        for (Vehicle vehicle : vehicles) {
            processVehicle(vehicle);
        }
    }

    // Pattern matching in switch expressions
    private static String describeVehicle(Vehicle vehicle) {
        return switch (vehicle) {
            case Car c -> "Car: " + c.make() + " " + c.model() + " (" + c.year() + ")";
            case Truck t -> "Truck: " + t.make() + " " + t.model() + " - " + t.payloadCapacity() + " lbs capacity";
            case Motorcycle m when m.hasSidecar() -> "Motorcycle with sidecar: " + m.make() + " " + m.model();
            case Motorcycle m -> "Motorcycle: " + m.make() + " " + m.model();
        };
    }

    // Pattern matching in switch statements with complex logic
    private static void processVehicle(Vehicle vehicle) {
        switch (vehicle) {
            case Car c when c.year() >= 2020 -> {
                System.out.println("Modern car detected: " + c.make() + " " + c.model());
                System.out.println("  Eligible for latest safety features");
            }
            case Car c -> {
                System.out.println("Classic car: " + c.make() + " " + c.model() + " (" + c.year() + ")");
                System.out.println("  Consider upgrading to newer model");
            }
            case Truck t when t.payloadCapacity() > 1500 -> {
                System.out.println("Heavy-duty truck: " + t.make() + " " + t.model());
                System.out.println("  Suitable for commercial use");
            }
            case Truck t -> {
                System.out.println("Light truck: " + t.make() + " " + t.model());
                System.out.println("  Good for personal and light commercial use");
            }
            case Motorcycle m when m.hasSidecar() -> {
                System.out.println("Touring motorcycle: " + m.make() + " " + m.model());
                System.out.println("  Perfect for two passengers");
            }
            case Motorcycle m -> {
                System.out.println("Standard motorcycle: " + m.make() + " " + m.model());
                System.out.println("  Ideal for solo riding");
            }
        }
    }

    // Pattern matching with records and instanceof
    public static void handleObject(Object obj) {
        switch (obj) {
            case String s -> System.out.println("String: " + s);
            case Integer i -> System.out.println("Integer: " + i);
            case Car c -> System.out.println("Car: " + c.make() + " " + c.model());
            case null -> System.out.println("Null value");
            default -> System.out.println("Unknown type: " + obj.getClass().getSimpleName());
        }
    }
}