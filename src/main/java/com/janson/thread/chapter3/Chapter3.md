## wait/notify/notifyAll 方法的使用注意事项？

1、为什么 wait 方法必须在 synchronized 保护的同步代码中使用？

```
synchronized (obj) {
while (<condition does not hold>)
obj.wait();
... // Perform action appropriate to condition
}

```
这里还存在一个“虚假唤醒”（spurious wakeup）的问题，线程可能在既没有被notify/notifyAll，
也没有被中断或者超时的情况下被唤醒，这种唤醒是我们不希望看到的。虽然在实际生产中，虚假唤醒发生的概率很小，但是程序依然需要保证在发生虚假唤醒的时候的正确性，所以就需要采用while循环的结构。
这样即便被虚假唤醒了，也会再次检查while里面的条件，如果不满足条件，就会继续wait，也就消除了虚假唤醒的风险。

2、为什么 wait/notify/notifyAll 被定义在 Object 类中，而 sleep 定义在 Thread 类中？
1）因为 Java 中每个对象都有一把称之为 monitor 监视器的锁，由于每个对象都可以上锁，这就要求在对象头中有一个用来保存锁信息的位置。
这个锁是对象级别的，而非线程级别的，wait/notify/notifyAll 也都是锁级别的操作，它们的锁属于对象，所以把它们定义在 Object 类中是最合适，因为 Object 类是所有对象的父类。

2）因为如果把 wait/notify/notifyAll 方法定义在 Thread 类中，会带来很大的局限性，比如一个线程可能持有多把锁，以便实现相互配合的复杂逻辑，
假设此时 wait 方法定义在 Thread 类中，如何实现让一个线程持有多把锁呢？又如何明确线程等待的是哪把锁呢？既然我们是让当前线程去等待某个对象的锁，自然应该通过操作对象来实现，而不是操作线程。



3、wait/notify 和 sleep 方法的异同？

相同点：

1）它们都可以让线程阻塞。

2）它们都可以响应 interrupt 中断：在等待的过程中如果收到中断信号， 都可以进行响应，并抛出 InterruptedException 异常。

不同点：

1、wait 方法必须在 synchronized 保护的代码中使用，而 sleep 方法并没有这个要求。

2、在同步代码中执行 sleep 方法时，并不会释放 monitor 锁，但执行 wait 方法时会主动释放 monitor 锁。

3、sleep 方法中会要求必须定义一个时间，时间到期后会主动恢复，而对于没有参数的 wait 方法而言，意味着永久等待，直到被中断或被唤醒才能恢复，它并不会主动恢复。

4、wait/notify 是 Object 类的方法，而 sleep 是 Thread 类的方法。