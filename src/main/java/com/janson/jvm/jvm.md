### 1、在JVM内存分配中，有几个参数是比较核心的

-Xms：Java堆内存的大小

-Xmx：Java堆内存的最大大小

-Xmn：Java堆内存中的新生代大小，扣除新生代剩下的就是老年代的内存大小了

-XX:PermSize：永久代大小

-XX:MaxPermSize：永久代最大大小

-Xss：每个线程的栈内存大小

-Xms和-Xmx分别用于设置Java堆内存的刚开始的大小，以及允许扩张到的最大大小。
对于这对参数，通常来说，都会设置为完全一样的大小。

-XX:PermSize和-XX:MaxPermSize，分别限定了永久代大小和永久代的最大大小
如果是JDK 1.8以后的版本，那么这俩参数被替换为了-XX:MetaspaceSize和-XX:MaxMetaspaceSize，但是大家至少
得知道，这两个参数限定了永久代的大小