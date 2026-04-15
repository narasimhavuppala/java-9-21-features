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

        // indent() - adds indentation to each line
        String indented = multiline.indent(4);
        System.out.println("Indented multiline text:");
        System.out.print(indented);

        // stripIndent() - removes common leading whitespace
        String indentedBlock = "    Line A\n    Line B\n    Line C";
        System.out.println("Stripped indent: '" + indentedBlock.stripIndent() + "'");

        // translateEscapes() - interprets escape sequences
        String escaped = "Line 1\\nLine 2\\tTabbed";
        System.out.println("Escaped text: '" + escaped.translateEscapes() + "'");

        // formatted() - formats string using format specifiers
        String user = "Alice";
        System.out.println("Formatted greeting: '" + "Hello, %s!".formatted(user) + "'");

        // lines() - returns a stream of lines
        System.out.println("Lines in multiline text:");
        multiline.lines().forEach(line -> System.out.println("  '" + line + "'"));
    }
}