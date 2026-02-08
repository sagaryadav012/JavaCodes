package org.example.streams;

public class PrimitiveStreams {
    static void main() {
        // IntStream, LongStream, DoubleStream are specialized streams for primitive types
        // They avoid the overhead of boxing/unboxing that occurs with Stream<Integer>, Stream<Long>, Stream<Double>

        // Example: IntStream
        System.out.println("IntStream:");
        java.util.stream.IntStream.rangeClosed(1, 5)
                .forEach(System.out::println);

        // Example: LongStream
        System.out.println("\nLongStream:");
        java.util.stream.LongStream.rangeClosed(1L, 5L)
                .forEach(System.out::println);

        // Example: DoubleStream
        System.out.println("\nDoubleStream:");
        java.util.stream.DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0)
                .forEach(System.out::println);
    }
}

