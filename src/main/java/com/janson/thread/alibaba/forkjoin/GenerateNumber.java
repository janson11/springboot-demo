package com.janson.thread.alibaba.forkjoin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/2 2:05 下午
 */
public class GenerateNumber {
    public static void main(String[] args) throws FileNotFoundException {
        Random random = new Random();
        try (PrintWriter out = new PrintWriter(new File("/Users/shanjian/Downloads/data1.txt"))) {
            for (int i = 0; i < 1000000; i++) {
                out.println(random.nextInt());
                if (i % 10000 == 0) {
                    out.flush();
                }
            }

        }
    }
}
