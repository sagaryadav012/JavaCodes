package org.example.streams;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamTerminal {
    static void main() {
        var list = Arrays.asList("Amit", "Riya", "Rohan", "Amit");

        long count = list.stream().filter(name -> name.startsWith("A")).count();
        System.out.printf("count of names starting with A: %d%n", count);

        Set<String> collect = list.stream().map(String::toUpperCase).collect(Collectors.toSet());
        System.out.println("unique names in uppercase: " + collect);
    }
}
