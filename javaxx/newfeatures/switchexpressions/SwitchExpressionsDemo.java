package javaxx.newfeatures.switchexpressions;

public class SwitchExpressionsDemo {
    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

    public static void main(String[] args) {
        Day today = Day.WEDNESDAY;

        // Traditional switch statement
        String type1 = getDayTypeTraditional(today);
        System.out.println("Traditional switch: " + type1);

        // Switch expression (arrow syntax)
        String type2 = getDayTypeExpression(today);
        System.out.println("Switch expression: " + type2);

        // Switch expression with yield
        String type3 = getDayTypeWithYield(today);
        System.out.println("Switch with yield: " + type3);

        // Switch expression returning different types
        int numericValue = getDayNumericValue(today);
        System.out.println("Numeric value: " + numericValue);
    }

    // Traditional switch statement
    private static String getDayTypeTraditional(Day day) {
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                return "Weekday";
            case SATURDAY:
            case SUNDAY:
                return "Weekend";
            default:
                throw new IllegalArgumentException("Invalid day: " + day);
        }
    }

    // Switch expression with arrow syntax
    private static String getDayTypeExpression(Day day) {
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
        };
    }

    // Switch expression with yield (for complex logic)
    private static String getDayTypeWithYield(Day day) {
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                System.out.println("Processing weekday: " + day);
                yield "Weekday";
            }
            case SATURDAY, SUNDAY -> {
                System.out.println("Processing weekend: " + day);
                yield "Weekend";
            }
        };
    }

    // Switch expression returning different types
    private static int getDayNumericValue(Day day) {
        return switch (day) {
            case MONDAY -> 1;
            case TUESDAY -> 2;
            case WEDNESDAY -> 3;
            case THURSDAY -> 4;
            case FRIDAY -> 5;
            case SATURDAY -> 6;
            case SUNDAY -> 7;
        };
    }
}