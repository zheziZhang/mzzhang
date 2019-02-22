package com.mzzhang.thread.matrix;

//import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixFileUtils {

    /**
     * 获取文件名
     * @param dirPath 文件夹路径
     * @return 文件名
     */
    public static File[] getFileNames(String dirPath){
        File file = new File(dirPath);
        if(!file.isDirectory()){
            return null;
        }
        return file.listFiles();
    }

    /**
     * 文件写入
     * @param resultArray 文件
     */
    public static void writeFile(String dirPath,int[][] resultArray){

        File file = new File(dirPath);
//        List<String> lines = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();
        for (int[] rows:resultArray) {
            for (int row :rows){
                sb.append(row+",");
            }
            sb.append("/r/n");
//            lines.add(sb.toString());
        }

//        try {
//            FileUtils.writeStringToFile(file,sb.toString(),"UTF-8");
////            FileUtils.writeLines(file,"UTF-8",sb.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
