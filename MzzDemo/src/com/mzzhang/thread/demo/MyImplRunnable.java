package com.mzzhang.thread.demo;

public class MyImplRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("this is thread by implements runnable");
    }
}
