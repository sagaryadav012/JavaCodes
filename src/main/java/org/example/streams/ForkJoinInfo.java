package org.example.streams;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ForkJoinInfo {
    static void main() {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        // This CPU has 10 cores, 6 performance cores and 4 efficiency cores
        // one performance core can do 2 tasks simultaneously means 2 threads for one performance core, and one efficiency core can do 1 task means 1 thread for one efficiency core

        // Total CPU cores (logical), Here availableProcessors equals to CPU cores (logical) which includes both performance and efficiency cores
        System.out.println("Available processors : "
                + Runtime.getRuntime().availableProcessors());

        // Max number of worker threads = availableProcessors - 1 (Java keeps one core free for main / OS)
        System.out.println("Parallelism level    : "
                + pool.getParallelism());

        // getPoolSize() - Actual number of worker threads created so far : Threads are often created lazily
        System.out.println("Pool size            : "
                + pool.getPoolSize());

        // getActiveThreadCount() - Number of threads currently executing tasks (active threads)
        System.out.println("Active thread count  : "
                + pool.getActiveThreadCount());

        System.out.println("Running thread count : "
                + pool.getRunningThreadCount());

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
        System.out.println("Pool size            : " + pool.getPoolSize());
        System.out.println("Active thread count  : " + pool.getActiveThreadCount());
    }
}

/*
-> It's default ForkJoinPool, it creats threads based on the number of available processors (logical cores) minus one, to keep one core free for the main thread and OS tasks. The pool size can grow lazily as needed, but it won't exceed the parallelism level. After executing a parallel stream, you may see that some threads are still active or running, depending on how the tasks are scheduled and completed.
-> We can customize the ForkJoinPool by creating our own instance with a specific parallelism level,
but the common pool is often sufficient for most use cases.
The common pool is shared across the entire application,
so be mindful of its usage in larger applications to avoid contention.
 */
