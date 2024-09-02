## CGLIB动态代理和JDK动态代理比较
1. JDK动态代理是实现了被代理对象的接口，CGLIB动态代理是继承了被代理对象
2. JDK和CGLIB都是在运行时生成字节码，JDK是直接生成Class字节码，CGLIB是使用ASM框架写Class字节码，CGLIB代理实现更复杂，因此生成代理类比JDK效率低
3. JDK调用代理方法是通过反射机制调用，而CGLIB是通过FastClass机制直接调用方法，因此CGLIB执行效率更高。

链接：https://www.jianshu.com/p/8aee43cbc373


## Spring 中的代理选择的原则
当Bean有实现接口时，Spring就会使用JDK的动态代理
当Bean没有实现接口时，Spring使用Cglib动态代理
Spring可以强制使用Cglib动态代理，只需要在Spring的配置文件中加如下代码
<aop:aspectj-autoproxy proxy-target-class="true"/>