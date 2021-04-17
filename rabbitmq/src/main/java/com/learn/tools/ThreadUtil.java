package com.learn.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {

    private static volatile ExecutorService pool = null;

    public static ExecutorService getPool() {
        if (pool == null) {
            synchronized (ThreadUtil.class) {
                if (pool == null) {
                    pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
                }
            }
        }
        return pool;
    }
}
