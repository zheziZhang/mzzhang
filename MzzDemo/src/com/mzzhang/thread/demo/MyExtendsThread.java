package com.mzzhang.thread.demo;

public class MyExtendsThread extends Thread{

    @Override
    public void run() {
        System.out.printf("this is a thread by extend thread");
    }
}
