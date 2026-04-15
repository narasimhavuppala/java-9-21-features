package javaxx.newfeatures.instanceofpatternmatching;

public class InstanceofPatternMatchingDemo {
    public static void main(String[] args) {
        Object obj1 = "Hello, World!";
        Object obj2 = 42;
        Object obj3 = 3.14;
        Object obj4 = new StringBuilder("Builder");

        // Traditional instanceof with cast
        System.out.println("Traditional instanceof:");
        processObjectTraditional(obj1);
        processObjectTraditional(obj2);
        processObjectTraditional(obj3);
        processObjectTraditional(obj4);

        System.out.println("\nPattern matching instanceof:");
        processObjectPattern(obj1);
        processObjectPattern(obj2);
        processObjectPattern(obj3);
        processObjectPattern(obj4);
    }

    // Traditional instanceof with explicit cast
    private static void processObjectTraditional(Object obj) {
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.println("String length: " + s.length());
        } else if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            System.out.println("Integer value: " + i);
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            System.out.println("Double value: " + d);
        } else {
            System.out.println("Unknown type: " + obj.getClass().getSimpleName());
        }
    }

    // Pattern matching instanceof (Java 14+)
    private static void processObjectPattern(Object obj) {
        if (obj instanceof String s) {
            System.out.println("String length: " + s.length());
        } else if (obj instanceof Integer i) {
            System.out.println("Integer value: " + i);
        } else if (obj instanceof Double d) {
            System.out.println("Double value: " + d);
        } else {
            System.out.println("Unknown type: " + obj.getClass().getSimpleName());
        }
    }

    // Pattern matching in conditional expressions
    public static boolean isLongString(Object obj) {
        return obj instanceof String s && s.length() > 10;
    }

    // Pattern matching with records (would be even more powerful)
    record Point(int x, int y) {}

    public static void processShape(Object shape) {
        if (shape instanceof Point p) {
            System.out.println("Point at (" + p.x() + ", " + p.y() + ")");
        } else if (shape instanceof String s) {
            System.out.println("Shape description: " + s);
        }
    }
}