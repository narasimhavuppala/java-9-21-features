package javaxx.newfeatures.simplewebserver;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleWebServerDemo {
    public static void main(String[] args) throws IOException {
        // Create a simple HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Create context for root path
        server.createContext("/", new RootHandler());

        // Create context for API endpoint
        server.createContext("/api/greet", new GreetHandler());

        // Create context for serving static files
        server.createContext("/static", new StaticFileHandler());

        // Set executor (null means default)
        server.setExecutor(null);

        // Start the server
        server.start();

        System.out.println("Server started on port 8080");
        System.out.println("Try these URLs:");
        System.out.println("  http://localhost:8080/");
        System.out.println("  http://localhost:8080/api/greet?name=Java");
        System.out.println("  http://localhost:8080/static");
        System.out.println("Press Ctrl+C to stop the server");
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Java 18+ Simple Web Server</title>
                </head>
                <body>
                    <h1>Welcome to Java Simple Web Server!</h1>
                    <p>This server is running on Java 18+ with the built-in HTTP server.</p>
                    <ul>
                        <li><a href="/api/greet?name=World">Greet API</a></li>
                        <li><a href="/static">Static Content</a></li>
                    </ul>
                </body>
                </html>
                """;

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length());

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    static class GreetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String name = "World"; // default

            if (query != null && query.startsWith("name=")) {
                name = query.substring(5);
            }

            String response = String.format("{\"message\": \"Hello, %s!\"}", name);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = """
                This is static content served by the Java HTTP server.
                In a real application, you could serve files from the file system.
                """;

            exchange.getResponseHeaders().set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, response.length());

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}

/*
 * Note: Java 18 introduced the 'jwebserver' command-line tool for serving static files:
 * jwebserver -p 8080 -d /path/to/directory
 *
 * This programmatic example shows how to create a custom web server using the built-in HTTP server.
 */