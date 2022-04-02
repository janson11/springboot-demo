package com.janson.thread.alibaba.forkjoin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/2 4:50 下午
 */
public class MergerFileSortTask extends RecursiveTask<String> {

    List<String> partFiles;
    int lo, hi;
    String filename;

    public MergerFileSortTask(List<String> partFiles, int lo, int hi, String filename) {
        this.partFiles = partFiles;
        this.lo = lo;
        this.hi = hi;
        this.filename = filename;
    }

    public MergerFileSortTask(List<String> partFiles, String filename) {
        this(partFiles, 0, partFiles.size(), filename);
    }

    @Override
    protected String compute() {
        int fileCount = hi - lo;
        // 如果要归并的文件数大于2，则fork
        if (fileCount > 2) {
            int mid = fileCount / 2;
            MergerFileSortTask task1 = new MergerFileSortTask(partFiles, lo, lo + mid, this.filename + "-1");
            MergerFileSortTask task2 = new MergerFileSortTask(partFiles, lo + mid, hi, this.filename + "-2");

            // 任务提交forkjoinPool中去执行
            task1.fork();
            task2.fork();

            // 合并两个文件
            try {
                this.mergerFile(task1.get(), task2.get());
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }


        } else if (fileCount == 2) {
            try {
                this.mergerFile(this.partFiles.get(lo), this.partFiles.get(lo + 1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (fileCount == 1) {
            return this.partFiles.get(lo);
        }
        return this.filename;
    }

    private void mergerFile(String f1, String f2) throws IOException {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(f1));
             BufferedReader reader2 = new BufferedReader(new FileReader(f2));
             PrintWriter pw = new PrintWriter(filename);
        ) {
            String s1 = reader1.readLine();
            String s2 = reader2.readLine();
            Integer d1 = s1 == null ? null : Integer.valueOf(s1);
            Integer d2 = s2 == null ? null : Integer.valueOf(s2);

            while (true) {
                if (s1==null) {
                    while (s2!=null) {
                        pw.println(s2);
                    }
                }
                if (s2==null) {
                    while (s1!=null) {
                        pw.println(s1);
                    }
                }
            }

        }

    }


}
