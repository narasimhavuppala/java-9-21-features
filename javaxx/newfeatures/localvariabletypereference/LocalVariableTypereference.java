package javaxx.newfeatures.localvariabletypereference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class LocalVariableTypereference {
    public static void main(String[] args) throws IOException {
        var myList = Arrays.asList("a", "b", "c");
        for (var element : myList) {
            System.out.println(element);
        }  // infers String



    }
}
