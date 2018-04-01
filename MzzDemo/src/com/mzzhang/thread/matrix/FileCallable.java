package com.mzzhang.thread.matrix;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by mzzhang on 2018/3/25.
 */
public class FileCallable implements Callable {

    private File file;

    public FileCallable(File file){
        this.file = file;
    }

    @Override
    public Object call() throws Exception {

        int[][] matrix = null;
        List<String> lines = FileUtils.readLines(file, "UTF-8");

        matrix = new int[lines.size()][lines.get(0).split(",").length];
        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i).split(",");
            for (int j = 0; j < line.length; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        return matrix;
    }
}
