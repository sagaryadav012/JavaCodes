package org.example.streams;

import java.util.List;
import java.util.stream.IntStream;

public class ParallelStream {
    static void main() {
        List<Integer> numbers = IntStream.rangeClosed(1, 10)
                .boxed()
                .toList();

        long start = System.currentTimeMillis();
        numbers.parallelStream().forEach(num -> {
            System.out.println(
                    "Number: " + num +
                            " | Thread: " + Thread.currentThread().getName()
            );

            try {
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " ms");


        numbers.stream().forEach(num -> {
            System.out.println(
                    "Number: " + num +
                            " | Thread: " + Thread.currentThread().getName()
            );
        });
    }
}
