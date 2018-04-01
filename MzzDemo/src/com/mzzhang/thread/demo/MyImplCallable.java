package com.mzzhang.thread.demo;

import java.util.concurrent.Callable;

public class MyImplCallable implements Callable {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println("this is implements callable and the thread Name: "+threadName);
        return threadName;
    }
}
