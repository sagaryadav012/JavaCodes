package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class SequentialStream {
    static void main() {
        List<String> names = Arrays.asList("A", "B", "C", "D");

        names.stream()
                .forEach(System.out::println); // Executes sequentially
    }
}
