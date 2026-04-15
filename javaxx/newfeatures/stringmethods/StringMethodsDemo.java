package javaxx.newfeatures.stringmethods;

public class StringMethodsDemo {
    public static void main(String[] args) {
        String text = "  Hello, World!  ";
        String multiline = "Line 1\nLine 2\nLine 3";

        // isBlank() - checks if string is empty or contains only whitespace
        System.out.println("Is blank: " + text.isBlank());
        System.out.println("Is blank (empty): " + "".isBlank());

        // strip() - removes leading and trailing whitespace
        System.out.println("Original: '" + text + "'");
        System.out.println("Stripped: '" + text.strip() + "'");

        // stripLeading() and stripTrailing()
        System.out.println("Strip leading: '" + text.stripLeading() + "'");
        System.out.println("Strip trailing: '" + text.stripTrailing() + "'");

        // repeat() - repeats the string n times
        String star = "*";
        System.out.println("Repeat 5 times: '" + star.repeat(5) + "'");

        // lines() - returns a stream of lines
        System.out.println("Lines in multiline text:");
        multiline.lines().forEach(line -> System.out.println("  '" + line + "'"));
    }
}