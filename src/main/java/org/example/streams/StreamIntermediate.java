package org.example.streams;

import java.util.List;

public class StreamIntermediate {
    static void main() {
        var list = List.of(10, 20, 30, 50, 50, 40, 20);

        list.stream().filter(num -> num > 30)
                .map(num -> num*2)
                .distinct()
                .sorted().forEach(System.out::println);
    }
}
