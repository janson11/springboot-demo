package com.janson.mutithread.basic.interview;

import com.janson.util.Print;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 线程池调用的面试题
 * <p>
 * 一个线程池的核心线程数 10 个，最大线程数 20 个，阻塞队列的容量为 30。
 * 现在提交 45 个任务，每个任务的耗时为 500ms。
 * 请问：这批任务执行完，总计需要多少时间？ 忽略线程创建、调度的耗时。
 * <p>
 * 好的，我们来详细分析这个问题。
 * <p>
 * 1. **核心线程数**：线程池有10个核心线程，这意味着一开始可以同时执行10个任务。
 * <p>
 * 2. **任务提交**：提交了45个任务，每个任务耗时500ms。
 * <p>
 * 3. **阻塞队列容量**：当核心线程都在忙碌时，新提交的任务会被放入阻塞队列中，队列容量为30。
 * <p>
 * 4. **最大线程数**：当阻塞队列满了之后，线程池会使用非核心线程（最大线程数 - 核心线程数，这里是20 - 10 = 10个）来执行任务。
 * <p>
 * 现在我们来计算总耗时：
 * <p>
 * - **第一阶段**：10个核心线程开始执行，每个任务耗时500ms，所以这10个任务会在5000ms（5s）内完成。
 * <p>
 * - **第二阶段**：当核心线程都在执行任务时，接下来的30个任务会被放入阻塞队列。由于核心线程数是10，所以这30个任务会分成3批执行：
 * - 第一批：10个任务，由10个核心线程执行，耗时5000ms（5s）。
 * - 第二批：接下来的10个任务，当第一批任务完成后，这10个任务会立即由核心线程执行，耗时同样是5000ms（5s）。
 * - 第三批：最后10个任务，当第二批任务完成后，这10个任务会立即由核心线程执行，耗时5000ms（5s）。
 * <p>
 * - **第三阶段**：当阻塞队列满了之后，剩下的5个任务会由额外的5个非核心线程执行。这5个任务耗时2500ms（2.5s）。
 * <p>
 * 现在，我们计算总耗时：
 * <p>
 * - 核心线程执行的三批任务总耗时：\( 5s + 5s + 5s = 15s \)。
 * - 非核心线程执行的5个任务耗时：\( 2.5s \)。
 * <p>
 * 但是，非核心线程的执行时间是并行于核心线程执行的第三批任务的，所以它不会增加总耗时。因此，总耗时实际上是核心线程执行的三批任务的总耗时，即：
 * <p>
 * \[ 15s \]
 * <p>
 * 所以，这批任务执行完，总计需要15秒。
 * @Author: Janson
 * @Date: 2024/8/18 11:18
 **/
public class ThreadPoolTest {


    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    @Test
    public void testThreadPoolScheduling() {

        Long totalTime = 0L;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(30), new ThreadPoolExecutor.AbortPolicy()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                START_TIME.set(System.currentTimeMillis());
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                long time = (START_TIME.get()) - System.currentTimeMillis();
                Print.tco("Task " + r.toString() + " executed in " + time + " ms");
                START_TIME.remove();
            }
        };


        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 45; i++) {
            int finalI = i;
            Future<Long> future = threadPoolExecutor.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    Print.tco("Task " + finalI + "  " + Thread.currentThread().getName() + " started");
                    Thread.sleep(500);
                    return 500L;
                }
            });

            try {
                totalTime += future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }

        Print.tco("Total time: " + totalTime + " ms");
        System.err.println("Total time [测试]: " + (System.currentTimeMillis() - startTime) + " ms");
    }


}
