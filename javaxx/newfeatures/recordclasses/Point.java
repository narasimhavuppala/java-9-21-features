package javaxx.newfeatures.recordclasses;

public record Point(int x, int y) {
    public static final Point ORIGIN = new Point(0, 0);

    public Point(int x, int y){
         if(x < 0 || y < 0){
             throw new IllegalArgumentException("Coordinates must be non-negative");
         }
        this.x = x;
        this.y = y;
    }

    // Calculate distance from origin
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    // Add two points
    public Point add(Point other) {
        return new Point(this.x + other.x, this.y + other.y);
    }

    // Scale the point by a factor
    public Point scale(int factor) {
        return new Point(this.x * factor, this.y * factor);
    }

    // Get the quadrant (1, 2, 3, 4, or 0 for origin/axis)
    public int quadrant() {
        if (x > 0 && y > 0) return 1;
        if (x < 0 && y > 0) return 2;
        if (x < 0 && y < 0) return 3;
        if (x > 0 && y < 0) return 4;
        return 0; // origin or on axis
    }

    // Custom toString
    @Override
    public String toString() {
        return String.format("Point(x=%d, y=%d)", x, y);
    }
}
class MainDemo {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        System.out.println("Point p1: " + p1);

        Point p2 = new Point(5, 15);
        System.out.println("Point p2: " + p2);

        // Distance from origin
        System.out.println("Distance of p1 from origin: " + p1.distanceFromOrigin());

        // Add points
        Point sum = p1.add(p2);
        System.out.println("Sum of p1 and p2: " + sum);

        // Scale point
        Point scaled = p1.scale(2);
        System.out.println("p1 scaled by 2: " + scaled);

        // Quadrant
        System.out.println("Quadrant of p1: " + p1.quadrant());

        // Origin point
        System.out.println("Origin: " + Point.ORIGIN);

        try {
            Point p3 = new Point(-5, 15);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating point p3: " + e.getMessage());
        }
    }
}
