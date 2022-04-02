package com.janson.thread.alibaba.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/2 4:14 下午
 */
public class ArrayMergeSortTask extends RecursiveAction {
    static final int THRESHOLD = 1000;

    final int[] array;
    final int lo, hi;

    public ArrayMergeSortTask(int[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }


    public ArrayMergeSortTask(int[] array) {
        this(array, 0, array.length);
    }

    @Override
    protected void compute() {
        if (hi - lo < THRESHOLD) {
            sortSequentially(lo, hi);
        } else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new ArrayMergeSortTask(array, lo, mid), new ArrayMergeSortTask(array, mid, hi));
            merge(lo, mid, hi);
        }

    }

    private void merge(int lo, int mid, int hi) {
        int[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++) {
            array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
        }

    }

    private void sortSequentially(int lo, int hi) {
        Arrays.sort(array, lo, hi);
    }

    public static void main(String[] args) {
        int length = 2_000;
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt();
            System.out.println(array[i]);
        }
    }
}
