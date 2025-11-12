package javaxx.newfeatures.recordclasses;

public record Point(int x, int y) {
    public Point(int x, int y){
         if(x < 0 || y < 0){
             throw new IllegalArgumentException("Coordinates must be non-negative");
         }
        this.x = x;
        this.y = y;
    }
}
class MainDemo {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        System.out.println("Point p1: " + p1);

        try {
            Point p2 = new Point(-5, 15);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating point p2: " + e.getMessage());
        }
    }
}
