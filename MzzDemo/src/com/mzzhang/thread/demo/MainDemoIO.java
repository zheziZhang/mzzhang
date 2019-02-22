package com.mzzhang.thread.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainDemoIO {
    public static void main(String[] args) {

/*        //1.继承Thread类
        runExtendsThread();*/

/*        //2.实现Runnable接口
        runImplRunnable();*/

/*        //3.实现Callable接口通过FutureTask包装器来创建Thread线程
        runCallable();*/

        //4.使用ExecutorService.Callable.Future实现返回结果的多线程
        runExecutorService();

/*        //5.使用ThreadPool创建线程池
        runThreadPoolExecutor();*/

/*        //6.使用SingleThreadExecutor创建线程池
        runSingleThreadExecutor();*/

/*        //7.使用FixedThreadPool创建线程池
        runFixedThreadPool();*/

/*        //8.使用CacheThreadPool方式创建线程池
        runCachedThreadPool();*/

        System.out.println("this is test");
    }

    /**
     * 使用CacheThreadPool方式创建线程池
     */
    private static void runCachedThreadPool() {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 15; i++) {
            MyThreadPoolTask task = new MyThreadPoolTask("CachedThreadPool",i);
            executorService.submit(task);
        }

        executorService.shutdown();
    }

    /**
     * 使用FixedThreadPool创建线程池
     */
    private static void runFixedThreadPool() {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 15; i++) {
            MyThreadPoolTask task = new MyThreadPoolTask("FixedThreadPool",i);
            executor.submit(task);
        }

        executor.shutdown();
    }

    /**
     * 使用SingleThreadExecutor创建线程池
     */
    private static void runSingleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 15; i++) {
            MyThreadPoolTask task = new MyThreadPoolTask("SingleThread",i);
            executor.submit(task);
        }
        executor.shutdown();
        System.out.println("executor.shutdown");
    }

    /**
     * 使用ThreadPool创建线程池
     */
    private static void runThreadPoolExecutor() {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 600,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        for (int i = 0; i < 15; i++) {
            MyThreadPoolTask task = new MyThreadPoolTask("ThreadPool",i);
            executor.execute(task);
            System.out.println(" 当前核心线程数："+executor.getCorePoolSize()+"当前线程池数："+executor.getPoolSize()+
                    "当前队列中等待线程数目："+executor.getQueue().size()+"当前激活的线程数："+executor.getActiveCount()+
                    "当前已完成线程数"+ executor.getCompletedTaskCount()+"当前任务总数："+executor.getTaskCount());
        }

        executor.shutdown();
    }

    /**
     *
     */
    private static void runExecutorService() {
        List<Future> futureList = new ArrayList<>();
        int nThreads = 15;
        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Callable callable1 = new MyImplCallable();
            //执行并获取返回值
            Future future = pool.submit(callable1);
            futureList.add(future);
            System.out.println("this is future list : " + i);
        }

        //关闭线程池
        pool.shutdown();

        for (Future future : futureList) {
            try {
                String value = (String) future.get();
                System.out.println("this is value " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现callable方式创建线程
     */
    private static void runCallable() {
        Callable callable = new MyImplCallable();
        FutureTask futureTask = new FutureTask(callable);
        Thread thread1 = new Thread(futureTask);
        thread1.start();
    }

    /**
     * 实现方式创建线程
     */
    private static void runImplRunnable() {
        MyImplRunnable myImplRunnable = new MyImplRunnable();
        Thread thread = new Thread(myImplRunnable);
        thread.start();
    }

    /**
     * 继承方式创建线程
     */
    private static void runExtendsThread() {
        MyExtendsThread myExtendsThread = new MyExtendsThread();
        myExtendsThread.start();
    }
}
