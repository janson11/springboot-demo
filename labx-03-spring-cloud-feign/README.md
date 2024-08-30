在 Feign 中，定义了四种日志级别：

- NONE：不打印日志
- BASIC：只打印基本信息，包括请求方法、请求地址、响应状态码、请求时长
- HEADERS：在 BASIC 基础信息的基础之上，增加请求头、响应头
- FULL：打印完整信息，包括请求和响应的所有信息。


① 对于 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 两个配置类，我们并没有添加 @Configuration 注解。

因为，Spring Boot 项目默认扫描 DemoConsumerApplication 所在包以及子包下的所有 Bean 们。而 @Configuration 注解也是一种 Bean，也会被扫描到。

如果添加 @Configuration 注解到 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 上，将会被 Spring Boot 所扫描到，导致整个项目的 Feign 客户端都使用相同的 Feign 配置，就无法到达 Feign 客户端级别的自定义配置的目的。



此，我们没有给 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 添加 @Configuration 注解。

友情提示，可以不看。

当然，如果胖友想要添加 @Configuration 注解到 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 上的话，还有一个不是很推荐的方案，将 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 移到和 DemoConsumerApplication 不同包中，避免被 Spring Boot 所扫描到。

例如说，在根路径下创建 feign 包，并将 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 放入其中。

② 为了避免多个 Feign 客户端级别的配置类创建的 Bean 之间互相冲突，Spring Cloud OpenFeign 通过 FeignContext 类，为每一个 Feign 客户端创建一个 Spring 子上下文。在 Spring Cloud OpenFeign 的设计中，Spring 的上下文设计特别有趣，胖友可以先暂时记住：

- 全局级别的 FeignClient 配置类是在 Spring 父上下文生效
- 客户端级别的 FeignClient 配置类在 Spring 子上下文生效。
不过这里要注意，因为 DefaultFeignClientConfiguration 和 DemoProviderFeignClientConfiguration 都创建了 Logger.Level Bean，而 DefaultFeignClientConfiguration 是在 Spring 父上下文生效，会和 DemoProviderFeignClientConfiguration 所在的 Spring 子上下文共享。

这样就导致从 Spring 获取 Logger.Level Bean 时，存在两个而不知道选择哪一个。因此，我们声明 DefaultFeignClientConfiguration 创建的 Logger.Level Bean 为 @Primary，优先使用它。


实践建议:
- 对于 Feign 自定义配置，推荐使用配置文件的方式，简单方便好管理。在配置文件的方式无法满足的情况下，使用 Spring JavaConfig 的方式作为补充。不过绝大多数场景下，都基本不需要哈~
- 配置文件方式的优先级高于 Spring JavaConfig 方式，客户端级别的优先级高于全局级别

在 Spring Cloud OpenFeign 官方文档有这么一段话：

FROM Feign Inheritance Support

It is generally not advisable to share an interface between a server and a client. It introduces tight coupling, and also actually doesn’t work with Spring MVC in its current form (method parameter mapping is not inherited).

意思是不推荐使用继承特性，因为通过 Java 接口的共享，导致服务提供者和消费者的耦合，而微服务的目的是为了服务提供者和消费者的解耦，存在一定的冲突。

不过实际场景下，蛮多公司采用继承特性，显而易见的好处，可以方便服务消费者的快速接入，基本无需编写额外的代码。

具体怎么选择，胖友可以自己进行评估，看看使用继承特性的情况下，在享受优点的同时，是否能够接受带来的缺点。

艿艿个人意见的话，是支持采用继承特性


拓展知识
- 文件上传	《Spring Cloud Feign 接口上传文件》 https://www.iocoder.cn/Fight/The-Spring-Cloud-Feign-interface-uploads-files/?self
- Form 表单提交	《Spring Cloud Feign Post 表单请求》 https://www.iocoder.cn/Fight/Spring-Cloud-Feign-Post-form-request/?self



## HTTP 客户端
   默认情况下，Feign 通过 JDK 自带的 HttpURLConnection 封装了 Client.Default，实现 HTTP 调用的客户端。因为 HttpURLConnection 缺少对 HTTP 连接池的支持，所以性能较低，在并发到达一定量级后基本会出现。

因此 Feign 提供了另外两个 HTTP 客户端：

ApacheHttpClient，基于 Apache HttpClient 封装
 - org.springframework.cloud.openfeign.ribbon.HttpClientFeignLoadBalancedConfiguration
OkHttpClient，基于 OkHttp 封装
 - org.springframework.cloud.openfeign.ribbon.OkHttpFeignLoadBalancedConfiguration


## Feign 和 Ribbon 都有请求重试的功能，两者都启用该功能的话，会产生冲突的问题。因此，有且只能启动一个的重试。目前比较推荐的是使用 Ribbon 来提供重试，如下是来自 Spring Cloud 开发者的说法：

FROM https://github.com/spring-cloud/spring-cloud-netflix/issues/467

### 在 Spring Cloud OpenFeign 中，默认创建的是 NEVER_RETRY 不进行重试。如此，我们只需要配置 Ribbon 的重试功能即可。