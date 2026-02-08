package org.example.streams;

import java.util.List;
import java.util.stream.IntStream;

public class StreamVsIntStreamBenchmark {

    private static final int SIZE = 10_000_000;

    static void main(String[] args) {

        // Prepare data
        List<Integer> list = IntStream.rangeClosed(1, SIZE)
                .boxed()
                .toList();

        // JVM warm-up (VERY important)
        for (int i = 0; i < 3; i++) {
            sumWithStream(list);
            sumWithIntStream();
        }

        // Measure Stream<Integer>
        long start1 = System.nanoTime();
        int sum1 = sumWithStream(list);
        long end1 = System.nanoTime();

        // Measure IntStream
        long start2 = System.nanoTime();
        int sum2 = sumWithIntStream();
        long end2 = System.nanoTime();

        System.out.println("Stream<Integer> sum   : " + sum1);
        System.out.println("Time taken            : " + (end1 - start1) / 1_000_000 + " ms");

        System.out.println();

        System.out.println("IntStream sum         : " + sum2);
        System.out.println("Time taken            : " + (end2 - start2) / 1_000_000 + " ms");
    }

    private static int sumWithStream(List<Integer> list) {
        return list.stream()
                .map(x -> x * 2)        // unboxing + boxing
                .reduce(0, Integer::sum);
    }

    private static int sumWithIntStream() {
        return IntStream.rangeClosed(1, SIZE)
                .map(x -> x * 2)    // pure primitive
                .sum();
    }
}
/*
Why do we need to warm up the JVM?
The JVM uses Just-In-Time (JIT) compilation to optimize code at runtime.
When you first run a method, it may be interpreted or compiled with less optimization.
As you run the same method multiple times, the JVM identifies "hot" code paths and optimizes them,
which can lead to significantly faster execution. By warming up the JVM, we allow it to optimize the code
before we take our measurements, giving us a more accurate benchmark of the performance difference
between Stream<Integer> and IntStream.

First execution
Bytecode → interpreted → slow

After several executions
Bytecode → JIT → optimized machine code


 */

