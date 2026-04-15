package javaxx.newfeatures.stringtemplates;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

// Note: String templates are a preview feature in Java 21+
// This example shows the concept - actual implementation requires preview features enabled
// and may not be available in all JDK versions

public class StringTemplatesDemo {
    public static void main(String[] args) {
        System.out.println("String Templates (Preview Feature in Java 21+)");
        System.out.println("============================================");
        System.out.println();
        System.out.println("String templates allow embedding expressions directly in string literals");
        System.out.println("using the STR template processor.");
        System.out.println();
        System.out.println("Example syntax (when available):");
        System.out.println("  String greeting = STR.\"Hello, \\{name}! You are \\{age} years old.\";");
        System.out.println("  String json = STR.\"\"\"");
        System.out.println("      {");
        System.out.println("          \"name\": \"\\{person.get(\"name\")}\",");
        System.out.println("          \"age\": \\{person.get(\"age\")}");
        System.out.println("      }");
        System.out.println("      \"\"\";");
        System.out.println();
        System.out.println("Benefits:");
        System.out.println("- Type-safe string interpolation");
        System.out.println("- No need for String.format() or concatenation");
        System.out.println("- Works with text blocks");
        System.out.println("- Compile-time validation of expressions");
        System.out.println();
        System.out.println("For now, traditional approaches like String.format() or text blocks are recommended.");

        // Demonstrate equivalent using current Java features
        demonstrateEquivalent();
    }

    private static void demonstrateEquivalent() {
        String name = "Alice";
        int age = 30;

        // Using String.format
        String greeting = String.format("Hello, %s! You are %d years old.", name, age);
        System.out.println("\nEquivalent using String.format:");
        System.out.println(greeting);

        // Using text blocks with formatted strings
        String json = """
            {
                "name": "%s",
                "age": %d,
                "city": "%s"
            }
            """.formatted("John Doe", 25, "New York");

        System.out.println("\nEquivalent using text blocks + formatted():");
        System.out.println(json);
    }
}