package javaxx.newfeatures.sealedclasses;

// Sealed class hierarchy for shapes
sealed abstract class Shape permits Circle, Rectangle, Triangle {
    public abstract double area();
}

final class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }
}

final class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

final class Triangle extends Shape {
    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }
}

public class SealedClassesDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape triangle = new Triangle(3.0, 4.0);

        System.out.println("Circle area: " + circle.area());
        System.out.println("Rectangle area: " + rectangle.area());
        System.out.println("Triangle area: " + triangle.area());

        // Exhaustive switch with sealed classes
        System.out.println("\nShape descriptions:");
        describeShape(circle);
        describeShape(rectangle);
        describeShape(triangle);
    }

    // Exhaustive switch - compiler ensures all permitted subclasses are handled
    private static void describeShape(Shape shape) {
        switch (shape) {
            case Circle c -> System.out.println("A circle with radius " + c.getRadius());
            case Rectangle r -> System.out.println("A rectangle " + r.getWidth() + "x" + r.getHeight());
            case Triangle t -> System.out.println("A triangle with base " + t.getBase() + " and height " + t.getHeight());
        }
    }
}