package com.hsns.base.manager;


import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class ExecutorsThreadManager {
    private static final String TAG = "ExecutorsThreadManager";
    private static final int corePoolSize = 4;
    private static final int maximumPoolSize = 32;
    private static final long keepAliveTime = 60;

    private static ExecutorsThreadManager mInstance;
    private static ThreadFactory mThreadFactory = new ThreadFactory() {
        // 一种通过线程安全的Integer
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            String name = "Task:" + mCount.getAndIncrement();
            Log.i(TAG, "ThreadName = " + name);
            return new Thread(r, name);
        }
    };
    private static BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(64);
    private static Executor threadPools = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
            TimeUnit.SECONDS, sPoolWorkQueue, mThreadFactory);


    public static ExecutorsThreadManager getInstance() {
        if (mInstance == null) {
            synchronized (ExecutorsThreadManager.class) {
                if (null == mInstance) {
                    mInstance = new ExecutorsThreadManager();
                }
            }
        }
        return mInstance;
    }

    public void submitThread(Runnable runnable) {
//        Log.d(TAG, "submitTread");
        try {
            threadPools.execute(runnable);
        } catch (RejectedExecutionException e) {
            Log.i(TAG, "RejectedExecutionException");
            e.printStackTrace();
        }
    }
}
