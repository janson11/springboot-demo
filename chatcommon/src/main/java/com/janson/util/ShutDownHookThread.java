package com.janson.util;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/2/28 22:22
 **/
public class ShutDownHookThread extends Thread {

    private volatile boolean hasShutdown = false;
    private static AtomicInteger shutdownTimes = new AtomicInteger(0);

    private final Callable callback;

    /**
     * Create the standard hook thread, with a call back, by using {@link Callable} interface.
     *
     * @param name
     * @param callable The call back function
     */
    public ShutDownHookThread(String name, Callable<Void> callable) {
        super("JVM退出钩子(" + name + ")");
        this.callback = callable;
    }


    /**
     * Thread run method
     * Invoke when the jvm shutdown
     */
    @Override
    public void run() {
        synchronized (this) {
            System.out.println(getName() + " starting.... ");
            if (!this.hasShutdown) {
                long beginTime = System.currentTimeMillis();
                try {
                    this.callback.call();
                } catch (Exception e) {
                    System.out.println(getName() + " error: " + e.getMessage());
                }
                long consumingTimeTotal = System.currentTimeMillis() - beginTime;
                System.out.println(getName() + " 耗时(ms): " + consumingTimeTotal);
            }
        }
    }
}
