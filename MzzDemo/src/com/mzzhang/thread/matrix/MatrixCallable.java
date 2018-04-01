package com.mzzhang.thread.matrix;

import java.util.List;
import java.util.concurrent.Callable;

public class MatrixCallable implements Callable {

    private final List<int[][]> matrixList;

    private int[] currentRow;

    private  int row;

    public MatrixCallable(List<int[][]> matrixList, int[] currentRow,int row){
        this.row = row;
        this.matrixList = matrixList;
        this.currentRow = currentRow;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public int[] call() throws Exception {

        System.out.println("thread:"+row +"begin");
        final int rowLength = currentRow.length;
        int[] resultRow = new int[rowLength];
        final int size = matrixList.size();
        int[][] matrix2;
        for (int i = 1 ;i< size;i++){
            matrix2 = matrixList.get(i);
            resultRow = new int[rowLength];
            for (int j = 0; j <rowLength; j++) {
                for (int k = 0; k < rowLength; k++) {
                    resultRow[j] += currentRow[k]* matrix2[k][j];
                }
            }
            currentRow = resultRow;
        }
        System.out.println("thread:"+row +":end");

        return  resultRow;

    }
}
