package org.example.streams;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamCreation {
    static void main() {
        // Create a stream from a collection
        var list = java.util.List.of("a", "b", "c");
        var streamFromCollection = list.stream();
        streamFromCollection.forEach(System.out::println);

        // Create a stream from an array
        var array = new String[]{"x", "y", "z"};
        var streamFromArray = Arrays.stream(array);
        streamFromArray.forEach(System.out::println);

        // Create a stream from values
        var streamFromValues = Stream.of("1", "2", "3");
        streamFromValues.forEach(System.out::println);

        // Create an infinite stream
        var infiniteStream = Stream.iterate(1, n -> n + 1).limit(5);
        infiniteStream.forEach(System.out::println);
    }
}
