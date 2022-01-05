package com.janson.thread.chapter22;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/1/5 4:25 下午
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
//        第一组：add、remove、element
//        addTest();
//        removeTest();
//        elementTest();

//     第二组：offer、poll、peek
//        offerTest();
//        pollTest();
//        peekTest();

//        第三组：put、take
//        putTest();
        takeTest();

    }

    /**
     * add 方法
     * add 方法是往队列里添加一个元素，如果队列满了，就会抛出异常来提示队列已满。示例代码如下：
     * Exception in thread "main" java.lang.IllegalStateException: Queue full
     */
    private static void addTest() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        blockingQueue.add(1);
        blockingQueue.add(1);
    }

    /**
     * Exception in thread "main" java.util.NoSuchElementException
     * remove 方法
     * remove 方法的作用是删除元素，如果我们删除的队列是空的，由于里面什么都没有，所以也无法删除任何元素，那么 remove 方法就会抛出异常
     */
    private static void removeTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        blockingQueue.add(1);
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }

    /**
     * Exception in thread "main" java.util.NoSuchElementException
     * element 方法是返回队列的头部节点，但是并不删除。和 remove 方法一样，如果我们用这个方法去操作一个空队列，想获取队列的头结点，可是由于队列是空的，我们什么都获取不到，会抛出和前面 remove 方法一样的异常：NoSuchElementException
     */
    private static void elementTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(4);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
        blockingQueue.add(4);
        System.out.println(blockingQueue.element());
    }


    /**
     * offer 方法用来插入一个元素，并用返回值来提示插入是否成功。如果添加成功会返回 true，
     * 而如果队列已经满了，此时继续调用 offer 方法的话，它不会抛出异常，只会返回一个错误提示：false
     */
    private static void offerTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
    }

    /**
     * poll 方法和第一组的 remove 方法是对应的，作用也是移除并返回队列的头节点。
     * 但是如果当队列里面是空的，没有任何东西可以移除的时候，便会返回 null 作为提示。正因如此，我们是不允许往队列中插入 null 的，否则我们没有办法区分返回的 null 是一个提示还是一个真正的元素
     */
    private static void pollTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3);
        blockingQueue.offer(1);
        blockingQueue.offer(2);
        blockingQueue.offer(3);
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }


    /**
     * peek 方法和第一组的 element 方法是对应的，意思是返回队列的头元素但并不删除。
     * 如果队列里面是空的，它便会返回 null 作为提示
     */
    private static void peekTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.offer(1);
        blockingQueue.offer(2);
        System.out.println(blockingQueue.peek());
    }

    /**
     * put 方法的作用是插入元素。通常在队列没满的时候是正常的插入，
     * 但是如果队列已满就无法继续插入，这时它既不会立刻返回 false 也不会抛出异常
     * ，而是让插入的线程陷入阻塞状态，直到队列里有了空闲空间，此时队列就会让之前的线程解除阻塞状态，并把刚才那个元素添加进去。
     * @throws InterruptedException
     */
    private static void putTest() throws InterruptedException {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.put(1);
        System.out.println(blockingQueue);
        blockingQueue.put(1);
        System.out.println(blockingQueue);
        blockingQueue.put(1);
        System.out.println(blockingQueue);

    }

    /**
     * take 方法的作用是获取并移除队列的头结点。通常在队列里有数据的时候会正常取出数据并删除；但是如果执行 take 的时候队列里无数据
     * ，则阻塞，直到队列里有数据；一旦队列里有数据了，就会立刻解除阻塞状态，并且取到数据。
     * @throws InterruptedException
     */
    private static void takeTest() throws InterruptedException {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.put(1);
        blockingQueue.put(2);
        System.out.println(blockingQueue);
        Integer take1 = blockingQueue.take();
        System.out.println("take1:"+take1);
        Integer take2 = blockingQueue.take();
        System.out.println("take2:"+take2);
        Integer take3 = blockingQueue.take();
        System.out.println("take3:"+take3);

    }
}
