package com.mzzhang.thread.demo;

public class MyThreadPoolTask implements Runnable{

    private String sType;
    private int nThreadTask;

    /**
     * 构造函数
     * @param sType 线程池类型
     * @param nThreadTask 当前运行线程taskNum
     */
    public MyThreadPoolTask(String sType,int nThreadTask){
        this.sType = sType;
        this.nThreadTask = nThreadTask;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        System.out.println("current threadPoolType is "+sType+"  current threadTask is " + nThreadTask+" is running");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("current threadPoolType is "+sType+"  current threadTask is " + nThreadTask+" is deal");
    }
}
