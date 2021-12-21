# Mybatis 

### Mybatis中的设计模式

### 1、适配器模式

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



JDK 动态代理的实现原理是：动态创建代理类，然后通过指定类加载器进行加载。在创建代理对象时，需要将 InvocationHandler 对象作为构造参数传入；当调用代理对象时，会调用 InvocationHandler.invoke() 方法，从而执行代理逻辑，最终调用真正业务对象的相应方法。



### 3、工厂方法

工厂方法模式中定义了 Factory 这个工厂接口，如下图所示，其中定义了 createProduct() 方法创建右侧继承树中的对象，不同的工厂接口实现类会创建右侧继承树中不同 Product 实现类（例如 ProductImpl 1 和 ProductImpl 2）。

![3.png](https://s0.lgstatic.com/i/image6/M01/04/52/CioPOWApSqKAQyYyAAD_0kpOQec437.png)

从上图中，我们可以看到工厂方法模式由四个核心角色构成。

Factory 接口：工厂方法模式的核心接口之一。使用方会依赖 Factory 接口创建 Product 对象实例。

Factory 实现类（图中的 FactoryImpl 1 和 FactoryImpl 2）：用于创建 Product 对象。不同的 Factory 实现会根据需求创建不同的 Product 实现类。

Product 接口：用于定义业务类的核心功能。Factory 接口创建出来的所有对象都需要实现 Product 接口。使用方依赖 Product 接口编写其他业务实现，所以使用方关心的是 Product 接口这个抽象，而不是其中的具体实现逻辑。

Product 实现类（图中的 ProductImpl 1 和 ProductImpl 2）：实现了 Product 接口中定义的方法，完成了具体的业务逻辑。

工厂方法模式最终也是符合“开放-封闭”原则的，可以通过添加新的 Factory 接口实现和 Product 接口实现来扩展整个体系的功能。另外，工厂方法模式对使用方暴露的是 Factory 和 Product 这两个抽象的接口，而不是具体的实现，也就帮助使用方面向接口编程。





JDBC 连接的创建是非常耗时的，从数据库这一侧看，能够建立的连接数也是有限的，所以在绝大多数场景中，我们都需要使用数据库连接池来缓存、复用数据库连接。

使用池化技术缓存数据库连接会带来很多好处，例如：

在空闲时段缓存一定数量的数据库连接备用，防止被突发流量冲垮；

实现数据库连接的重用，从而提高系统的响应速度；

控制数据库连接上限，防止连接过多造成数据库假死；

统一管理数据库连接，避免连接泄漏。



### 4、装饰器模式

装饰器模式就是一种通过组合方式实现扩展的设计模式，它可以完美地解决上述功能增强的问题。装饰器的核心思想是为已有实现类创建多个包装类，由这些新增的包装类完成新需求的扩展。

装饰器模式使用的是组合方式，相较于继承这种静态的扩展方式，装饰器模式可以在运行时根据系统状态，动态决定为一个实现类添加哪些扩展功能。

装饰器模式的核心类图，如下所示：

![Drawing 1.png](https://s0.lgstatic.com/i/image6/M01/05/74/Cgp9HWAwwdGAWEZ4AAG1zgT1MQM431.png)

从图中可以看到，装饰器模式中的核心类主要有下面四个。

Component 接口：已有的业务接口，是整个功能的核心抽象，定义了 Decorator 和 ComponentImpl 这些实现类的核心行为。JDK 中的 IO 流体系就使用了装饰器模式，其中的 InputStream 接口就扮演了 Component 接口的角色。

ComponentImpl 实现类：实现了上面介绍的 Component 接口，其中实现了 Component 接口最基础、最核心的功能，也就是被装饰的、原始的基础类。在 JDK IO 流体系之中的 FileInputStream 就扮演了 ComponentImpl 的角色，它实现了读取文件的基本能力，例如，读取单个 byte、读取 byte[] 数组。

Decorator 抽象类：所有装饰器的父类，实现了 Component 接口，其核心不是提供新的扩展能力，而是封装一个 Component 类型的字段，也就是被装饰的目标对象。需要注意的是，这个被装饰的对象可以是 ComponentImpl 对象，也可以是 Decorator 实现类的对象，之所以这么设计，就是为了实现下图的装饰器嵌套。这里的 DecoratorImpl1 装饰了 DecoratorImpl2，DecoratorImpl2 装饰了 ComponentImpl，经过了这一系列装饰之后得到的 Component 对象，除了具有 ComponentImpl 的基础能力之外，还拥有了 DecoratorImpl1 和 DecoratorImpl2 的扩展能力。JDK IO 流体系中的 FilterInputStream 就扮演了 Decorator 的角色。



1. BlockingCache
顾名思义，BlockingCache 是在原有 Cache 实现之上添加了阻塞线程的特性。

最终，我们得到 BlockingCache 的核心原理如下图所示：

![Drawing 4.png](https://s0.lgstatic.com/i/image6/M00/05/72/CioPOWAwwimAGb51AAJd328lR7k035.png)

2. FifoCache

   当 Cache 中的缓存条目达到上限的时候，则会将 Cache 中最早写入的缓存条目清理掉，这也就是先入先出的基本原理。