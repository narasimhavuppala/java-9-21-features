# Java 9-21 Features Examples

This project demonstrates various features introduced in Java 9 through Java 21.

## Project Structure

```
javaxx/
├── module-info.java
└── newfeatures/
    ├── localvariabletypereference/     # Java 10: Local Variable Type Inference (var)
    ├── recordclasses/                  # Java 14: Records
    ├── textblocks/                     # Java 13: Text Blocks
    ├── virtualthreads/                 # Java 19: Virtual Threads
    ├── privatemethodsinterfaces/       # Java 9: Private Methods in Interfaces
    ├── stringmethods/                  # Java 11: String Methods (isBlank, lines, strip, repeat)
    ├── httpclient/                     # Java 11: HTTP Client API
    ├── switchexpressions/              # Java 12/14: Switch Expressions
    ├── instanceofpatternmatching/      # Java 14: instanceof Pattern Matching
    ├── sealedclasses/                  # Java 15/17: Sealed Classes
    ├── patternmatchingswitch/          # Java 17: Pattern Matching for Switch
    ├── simplewebserver/                # Java 18: Simple Web Server (programmatic example)
    ├── stringtemplates/                # Java 21: String Templates (preview - conceptual example)
    └── sequencedcollections/           # Java 21: Sequenced Collections
```

## How to Compile and Run

### Compile the Project
```bash
javac -d out --module-source-path . --module javaxx
```

### Run Individual Examples
```bash
# Example: Run String Methods demo
java --module-path out -m javaxx/javaxx.newfeatures.stringmethods.StringMethodsDemo

# Example: Run Switch Expressions demo
java --module-path out -m javaxx/javaxx.newfeatures.switchexpressions.SwitchExpressionsDemo

# Example: Run Records demo
java --module-path out -m javaxx/javaxx.newfeatures.recordclasses.MainDemo
```

## Features Covered

### Java 9
- **Modules (Jigsaw)**: Module system for better encapsulation
- **Private Methods in Interfaces**: Allow private methods in interfaces for code reuse

### Java 10
- **Local Variable Type Inference (var)**: Automatic type inference for local variables

### Java 11
- **String Methods**: `isBlank()`, `lines()`, `strip()`, `repeat()`
- **HTTP Client API**: Modern HTTP client for making web requests

### Java 12/13/14
- **Switch Expressions**: Enhanced switch statements that can return values
- **Text Blocks**: Multi-line string literals (preview in 13, standard in 15)
- **instanceof Pattern Matching**: Type checking and casting in one operation

### Java 15/17
- **Sealed Classes**: Restrict which classes can extend/implement them
- **Pattern Matching for Switch**: Use patterns in switch expressions

### Java 18
- **Simple Web Server**: Built-in HTTP server for serving static content

### Java 19
- **Virtual Threads**: Lightweight threads for concurrency

### Java 21
- **String Templates**: Type-safe string interpolation (preview)
- **Sequenced Collections**: New interfaces for ordered collections with first/last access

## Notes

- String Templates are a preview feature and may require `--enable-preview` flag in future JDK versions
- Some features were introduced as preview features in earlier versions and became standard later
- The project uses Java modules for better organization and encapsulation