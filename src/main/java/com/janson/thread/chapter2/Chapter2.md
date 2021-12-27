## 如何正确停止线程？为什么 volatile 标记位的停止方法是错误的？

1 、对于 Java 而言，最正确的停止线程的方式是使用 interrupt。但 interrupt 仅仅起到通知被停止线程的作用。而对于被停止的线程而言，它拥有完全的自主权，它既可以选择立即停止，也可以选择一段时间后停止，也可以选择压根不停止。

2、如何用interrupt停止线程
`while (!Thread.currentThread().isInterrupted() && more work to do) {
do more work
}`



3、sleep 期间能否感受到中断
``
Runnable runnable = () -> {

int num = 0;

try {

while (!Thread.currentThread().isInterrupted() &&

num <= 1000) {

System.out.println(num);

num++;

Thread.sleep(1000000);

}

} catch (InterruptedException e) {

e.printStackTrace();

}

};

``

4、两种最佳处理方式
* 方法签名抛异常，run()强制try/catch

不好的方式
```
void subTas() {

    try {

        Thread.sleep(1000);

    } catch (InterruptedException e) {

        // 在这里不处理该异常是非常不好的

    }

}

```

建议的方式
```
void subTask2() throws InterruptedException {

    Thread.sleep(1000);

}

```
* 再次中断
```
private void reInterrupt() {

    try {

        Thread.sleep(2000);

    } catch (InterruptedException e) {

        Thread.currentThread().interrupt();

        e.printStackTrace();

    }

}

```

5、为什么用 volatile 标记位的停止方法是错误的

首先，我们来看几种停止线程的错误方法。比如 stop()，suspend() 和 resume()，这些方法已经被 Java 直接标记为 @Deprecated。如果再调用这些方法，IDE 会友好地提示，我们不应该再使用它们了。但为什么它们不能使用了呢？是因为 stop() 会直接把线程停止，这样就没有给线程足够的时间来处理想要在停止前保存数据的逻辑，任务戛然而止，会导致出现数据完整性等问题。

而对于 suspend() 和 resume() 而言，它们的问题在于如果线程调用 suspend()，它并不会释放锁，就开始进入休眠，但此时有可能仍持有锁，这样就容易导致死锁问题，因为这把锁在线程被 resume() 之前，是不会被释放的。

假设线程 A 调用了 suspend() 方法让线程 B 挂起，线程 B 进入休眠，而线程 B 又刚好持有一把锁，此时假设线程 A 想访问线程 B 持有的锁，但由于线程 B 并没有释放锁就进入休眠了，所以对于线程 A 而言，此时拿不到锁，也会陷入阻塞，那么线程 A 和线程 B 就都无法继续向下执行。

正是因为有这样的风险，所以 suspend() 和 resume() 组合使用的方法也被废弃了。那么接下来我们来看看，为什么用 volatile 标记位的停止方法也是错误的？


## summary 
1.正确停止线程的方法： thread.interrupt()，通知线程中断； 2.线程内逻辑需配合响应中断：1）正常执行循环中使用 Thread.currentThread().isInterrupted()判断中断标识; 2)若含有sleep()等Waiting操作,会唤醒线程，抛出interruptedException，抛出后中断标识会重置。对于中断异常，要么正确处理，重新设置中断标识；要么在方法上声明抛出异常以便调用方处理。3.为什么用 volatile 标记位的停止方法是错误的例如 
生产-消费模式，含有阻塞put操作时，volatile 标记变量改变也无法唤醒阻塞中的生产者线程。4.stop()、suspend() 和 resume()是已过期方法，有很大安全风险，它们强行停止线程，有可能造成线程持有的锁或资源没有释放。