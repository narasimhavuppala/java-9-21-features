package javaxx.newfeatures.sealedclasses;

// Sealed class hierarchy for shapes
sealed abstract class Shape permits Circle, Rectangle, Triangle, Polygon, FreeformShape {
    public abstract double area();
}

sealed abstract class Polygon extends Shape permits Square, EquilateralTriangle {
}

final class Square extends Polygon {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }

    public double getSide() {
        return side;
    }
}

final class EquilateralTriangle extends Polygon {
    private final double side;

    public EquilateralTriangle(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return Math.sqrt(3) / 4 * side * side;
    }

    public double getSide() {
        return side;
    }
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

non-sealed class FreeformShape extends Shape {
    private final double area;

    public FreeformShape(double area) {
        this.area = area;
    }

    @Override
    public double area() {
        return area;
    }
}

final class SpecialQuadrilateral extends FreeformShape {
    private final double width;
    private final double height;

    public SpecialQuadrilateral(double width, double height) {
        super(width * height);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
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
        Shape square = new Square(4.0);
        Shape equilateral = new EquilateralTriangle(3.0);
        Shape custom = new SpecialQuadrilateral(5.0, 2.0);

        System.out.println("Circle area: " + circle.area());
        System.out.println("Rectangle area: " + rectangle.area());
        System.out.println("Triangle area: " + triangle.area());
        System.out.println("Square area: " + square.area());
        System.out.println("Equilateral triangle area: " + equilateral.area());
        System.out.println("Special quadrilateral area: " + custom.area());

        // Exhaustive switch with sealed classes
        System.out.println("\nShape descriptions:");
        describeShape(circle);
        describeShape(rectangle);
        describeShape(triangle);
        describeShape(square);
        describeShape(equilateral);
        describeShape(custom);

        // Pattern matching with sealed classes
        System.out.println("\nAdvanced descriptions:");
        advancedDescribe(square);
        advancedDescribe(equilateral);
        advancedDescribe(custom);
    }

    // Exhaustive switch - compiler ensures all permitted subclasses are handled
    private static void describeShape(Shape shape) {
        switch (shape) {
            case Circle c -> System.out.println("A circle with radius " + c.getRadius());
            case Rectangle r -> System.out.println("A rectangle " + r.getWidth() + "x" + r.getHeight());
            case Triangle t -> System.out.println("A triangle with base " + t.getBase() + " and height " + t.getHeight());
            case Square s -> System.out.println("A square with side " + s.getSide());
            case EquilateralTriangle e -> System.out.println("An equilateral triangle with side " + e.getSide());
            case SpecialQuadrilateral q -> System.out.println("A special quadrilateral " + q.getWidth() + "x" + q.getHeight());
            case FreeformShape f -> System.out.println("A freeform shape with area " + f.area());
        }
    }

    private static void advancedDescribe(Shape shape) {
        if (shape instanceof Square s) {
            System.out.println("Pattern match: square side = " + s.getSide());
        } else if (shape instanceof EquilateralTriangle e) {
            System.out.println("Pattern match: equilateral triangle side = " + e.getSide());
        } else if (shape instanceof SpecialQuadrilateral q) {
            System.out.println("Pattern match: special quadrilateral " + q.getWidth() + "x" + q.getHeight());
        } else {
            System.out.println("Pattern match: other shape area = " + shape.area());
        }
    }
}