package com.mzzhang.thread.matrix;

import java.util.concurrent.Callable;

public class MatrixCallable2 implements Callable {

    private int[][] matrix1;

    private int[][] matrix2;

    public MatrixCallable2(int[][] matrix1,int[][] matrix2){

        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        int[][] resultArray = new int[matrix1.length][matrix2[0].length];
        for(int i=0; i<matrix1.length; i++){
            for(int j=0; j<matrix2[0].length; j++){
                for(int k=0; k<matrix1.length; k++){
                    resultArray[i][j] += matrix1[i][k]*matrix2[k][j];
                }
            }
        }
        return resultArray;
    }
}
