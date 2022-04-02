package com.janson.thread.alibaba.forkjoin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/2 2:10 下午
 */
public class SortByForkJoin {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        // 1 forkjoin线程池
        ForkJoinPool pool = new ForkJoinPool();

        // 2 从文件中读取内存容量可处理的数据量 拆分为多个部分排序的文件
        int size = 100_000;
        int[] array = new int[size];
        BufferedReader reader = new BufferedReader(new FileReader("/Users/shanjian/Downloads/data1.txt"));
        String line = null;
        int i=0;
        int partition = 0;

        // 3 拆分的部分文件名list 后面归并部分文件需要用到它
        List<String> partFiles = new ArrayList<>();
        while ((line=reader.readLine()) !=null) {
            array[i++] = Integer.parseInt(line);
            if (i==size) {
                i=0;
                // 排序输出到部分文件
                String filename = "/Users/shanjian/Downloads/data/data1-part-"+partition++ +".txt";
                doPartitionSort(pool,filename,array,0,size);
                partFiles.add(filename);

            }
        }
        reader.close();

        // 4 可能有未达到size的部分数据
        if (i>0){
            // 排序输出到部分文件
            String filename = "/Users/shanjian/Downloads/data/data1-part-"+partition++ +".txt";
            doPartitionSort(pool,filename,array,0,size);
            partFiles.add(filename);
        }

        // 归并部分文件为一个排序的文件
        if (partition >1) {
            MergerFileSortTask mtask = new MergerFileSortTask(partFiles,"/Users/shanjian/Downloads/data/data1-sort");
            pool.submit(mtask);
            mtask.get();
        }

        pool.shutdown();


    }

    private static void doPartitionSort(ForkJoinPool pool, String filename, int[] array, int start, int end) throws FileNotFoundException, ExecutionException, InterruptedException {
        ArrayMergeSortTask task = new ArrayMergeSortTask(array,start,end);
        pool.submit(task);
        task.get();

        // 输出到文件
        try(PrintWriter pw = new PrintWriter(filename)){
            for (int i = start; i <end ; i++) {
                pw.println(array[i]);
            }
        }
    }
}
