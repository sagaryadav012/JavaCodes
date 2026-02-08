package org.example.streams;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class CustomForkJoinPool {
    static void main() {

        // set the parallelism level to 4, which means the common pool will use at most 4 worker threads for parallel tasks
        System.setProperty(
                "java.util.concurrent.ForkJoinPool.common.parallelism", "4"
        );

        ForkJoinPool pool = ForkJoinPool.commonPool();

        // Total CPU cores (logical), Here availableProcessors equals to CPU cores (logical) which includes both performance and efficiency cores
        System.out.println("Available processors : "
                + Runtime.getRuntime().availableProcessors());

        // Max number of worker threads = availableProcessors - 1 (Java keeps one core free for main / OS)
        System.out.println("Parallelism level    : "
                + pool.getParallelism());

        // getPoolSize() - Actual number of worker threads created so far : Threads are often created lazily
        System.out.println("Pool size            : "
                + pool.getPoolSize());

        // trigger parallel stream
        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    System.out.println(
                            "Task " + i +
                                    " | Thread: " + Thread.currentThread().getName()
                    );
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println("\nAfter execution:");
        // pool size would be max 4 because we set the parallelism level to 4
        System.out.println("Pool size            : " + pool.getPoolSize());
    }
}
