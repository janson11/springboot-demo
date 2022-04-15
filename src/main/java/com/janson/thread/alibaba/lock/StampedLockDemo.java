package com.janson.thread.alibaba.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @Description: 邮戳锁代码示例
 * @Author: shanjian
 * @Date: 2022/4/15 11 上午
 */
public class StampedLockDemo {

    class Point {
        private double x, y;
        private final StampedLock s1 = new StampedLock();


        // method is modifying x and y ,needs exclusive lock 修改,需要独占锁
        public void move(double deltaX, double deltaY) {
            long stamp = s1.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                s1.unlockWrite(stamp);
            }
        }

        // 对于有条件的状态改变，需要将读锁转为写锁，如下代码：
        public void moveIfAt(double oldX, double oldY, double newX, double newY) {
            // 获取一个读悲观锁
            long stamp = s1.readLock();
            try {
                // 循环，检查当前状态是否符合
                while (x == oldX && y == oldY) {
                    // 将读锁转为写锁
                    long writeStamp = s1.tryConvertToWriteLock(stamp);
                    // 确认转为写锁是否成功
                    if (writeStamp != 0) {
                        // 如果成功，替换票据
                        stamp = writeStamp;
                        // 进行状态改变
                        x = newX;
                        y = newY;
                        break;
                    } else {
                        // 如果不能转化成功为写锁
                        // 显示释放读锁
                        s1.unlockRead(stamp);
                        // 显示直接进行写锁，然后再通过循环再试
                        stamp = s1.writeLock();
                    }

                }
            } finally {
                // 释放读锁或者写锁。
                s1.unlock(stamp);
            }

        }

        /**
         * 乐观读锁案例
         */
        public double distanceFromOrigin() {
            // 获得一个乐观读锁
            long stamp = s1.tryOptimisticRead();
            // 将两个字段读入本地局部变量
            double currentX = x, currentY = y;
            // 检查发出乐观读锁后同时是否有其他写锁发生
            if (!s1.validate(stamp)) {
                // 如果有，我们再次获取一个读悲观锁
                stamp = s1.readLock();
                try {
                    // 将两个字段读入本地局部变量
                    currentX = x;
                    currentY = y;
                } finally {
                    //释放读锁
                    s1.unlockRead(stamp);
                }

            }
            return Math.sqrt(currentX * currentX + currentY * currentY);

        }


    }
}
