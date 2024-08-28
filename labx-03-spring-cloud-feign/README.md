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