# Mybatis 

### Mybatis中的设计模式

#### 1、适配器模式

适配器模式主要解决的是由于接口不能兼容而导致类无法使用的问题，这在处理遗留代码以及集成第三方框架的时候用得比较多。其核心原理是：通过组合的方式，将需要适配的类转换成使用者能够使用的接口。

![2.png](https://s0.lgstatic.com/i/image6/M00/03/97/Cgp9HWAfYoOAKO6lAAEyIgsMVKA161.png)

目标接口（Target）：使用者能够直接使用的接口。以处理遗留代码为例，Target 就是最新定义的业务接口。

需要适配的类/要使用的实现类（Adaptee）：定义了真正要执行的业务逻辑，但是其接口不能被使用者直接使用。这里依然以处理遗留代码为例，Adaptee 就是遗留业务实现，由于编写 Adaptee 的时候还没有定义 Target 接口，所以 Adaptee 无法实现 Target 接口。

适配器（Adapter）：在实现 Target 接口的同时，维护了一个指向 Adaptee 对象的引用。Adapter 底层会依赖 Adaptee 的逻辑来实现 Target 接口的功能，这样就能够复用 Adaptee 类中的遗留逻辑来完成业务。

优点：

适配器模式带来的最大好处就是复用已有的逻辑，避免直接去修改 Adaptee 实现的接口，这符合开放-封闭原则（也就是程序要对扩展开放、对修改关闭）。

### 2、代理模式

1. 静态代理模式

![4.png](https://s0.lgstatic.com/i/image6/M00/03/97/Cgp9HWAfYrOAWv7JAAD2hkpzuWw664.png)

从该类图中，你可以看到与代理模式相关的三个核心角色。

Subject：程序中的业务接口，定义了相关的业务方法。

RealSubject：实现了 Subject 接口的业务实现类，其实现中完成了真正的业务逻辑。

Proxy：代理类，实现了 Subject 接口，其中会持有一个 Subject 类型的字段，指向一个 RealSubject 对象。



Proxy 能够控制使用方对 RealSubject 对象的访问，或是在执行业务逻辑之前执行统一的预处理逻辑，在执行业务逻辑之后执行统一的后置处理逻辑。

代理模式除了实现访问控制以外，还能用于实现延迟加载。