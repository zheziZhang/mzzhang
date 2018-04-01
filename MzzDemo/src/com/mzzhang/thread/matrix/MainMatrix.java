package com.mzzhang.thread.matrix;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainMatrix {

    /**
     * 运行线程数
     */
    private static final int THREAD_SIZE = 8;

    private static int arraySize;

    /**
     * 原始文件存放路径
     */
    private static String DIR_PATH = "F:\\文档资料\\培训资料\\Thread\\data\\1024_5";

    /**
     * 结果文件存放目录
     */
    private static String RESULT_DIR_PATH = "F:\\文档资料\\培训资料\\Thread\\data\\result\\1024_5.txt";

    private static List<int[][]> oriMatrixList;

    public static void main(String[] args) {

        System.out.println("this is begin!");
        Long startTime = System.currentTimeMillis();

        oriMatrixList = getOriginalMatrixList();
        arraySize = oriMatrixList.size();
        System.out.println("deal file times : "+(System.currentTimeMillis() - startTime ));

        int[][] resultList = runMatrixCallable();
//        int[][] resultList = runMatrixCallable2();

//        System.out.println("写入等待 : "+(System.currentTimeMillis() - startTime ));
        MatrixFileUtils.writeFile(RESULT_DIR_PATH,resultList);
        System.out.println("this is end " + (System.currentTimeMillis() - startTime ));
    }

    private static int[][] runMatrixCallable2(){

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_SIZE);

        int[][] resultArray = oriMatrixList.get(0);
        for (int i = 1; i < arraySize; i++) {

            List<Future<int[][]>> resultFutureList = new ArrayList<Future<int[][]>>();
            MatrixCallable2 matrix2 = new MatrixCallable2(resultArray,oriMatrixList.get(0));
            resultFutureList.add(executorService.submit(matrix2));

        }

        return null;
    }

    /**
     * 创建矩阵处理线程
     * 按行计算
     * 每次线程得出一行的结果后
     * 将该行与下个矩阵相乘得出下个一行的值
     * 直到完全把改行计算出来
     * @return
     */
    private static int[][] runMatrixCallable() {

        Long startTime = System.currentTimeMillis();
        int[][] resultMatrix = new int[oriMatrixList.get(0).length][oriMatrixList.get(0)[0].length];

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_SIZE);

        List<Future<int[]>> resultFutureList = new ArrayList<Future<int[]>>();
        final int length = oriMatrixList.get(0).length;

        for (int i = 0; i < length; i++) {
            resultFutureList.add(executorService.submit(new MatrixCallable(oriMatrixList,oriMatrixList.get(0)[i],i)));
        }

        System.out.println("矩阵总入队时间:"+(System.currentTimeMillis()-startTime));

        try {
            int[] result;
            int size = resultFutureList.size();
            for (int i = 0; i < size; i++) {
                result =  resultFutureList.get(i).get();
                resultMatrix[i] = result;
                System.out.println("第"+i+"future 等待时常:"+(System.currentTimeMillis()-startTime));
            }
            System.out.println("等待总时常:"+(System.currentTimeMillis()-startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        return resultMatrix;
    }

    /**\
     * 获取原生的矩阵
     * @return 矩阵list
     */
    public static List<int[][]> getOriginalMatrixList(){

        File[] files = MatrixFileUtils.getFileNames(DIR_PATH);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_SIZE);

        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            FileCallable fileCallable = new FileCallable(files[i]);
            futureList.add(executor.submit(fileCallable));
        }

        List<int[][]> matrixList = new ArrayList<>();
        for (Future future :
                futureList) {
            try {
                matrixList.add((int[][]) future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return matrixList;
    }

}
