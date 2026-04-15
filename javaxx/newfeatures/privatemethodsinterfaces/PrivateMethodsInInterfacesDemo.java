package javaxx.newfeatures.privatemethodsinterfaces;

interface Calculator {
    default int add(int a, int b) {
        validateInputs(a, b);
        return a + b;
    }

    default int multiply(int a, int b) {
        validateInputs(a, b);
        return a * b;
    }

    private void validateInputs(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Inputs must be non-negative");
        }
    }
}

public class PrivateMethodsInInterfacesDemo implements Calculator {
    public static void main(String[] args) {
        Calculator calc = new PrivateMethodsInInterfacesDemo();

        System.out.println("Addition: " + calc.add(5, 3));
        System.out.println("Multiplication: " + calc.multiply(4, 7));

        try {
            calc.add(-1, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}