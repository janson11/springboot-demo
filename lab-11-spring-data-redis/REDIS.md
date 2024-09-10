# Redis 提供了 PUB/SUB 订阅功能，
## 实际我们在使用时，一定要注意，它提供的不是一个可靠的订阅系统。
例如说，有消息 PUBLISH 了，Redis Client 因为网络异常断开，无法订阅到这条消息。等到网络恢复后，Redis Client 重连上后，是无法获得到该消息的。相比来说，成熟的消息队列提供的订阅功能，因为消息会进行持久化（Redis 是不持久化 Publish 的消息的），并且有客户端的 ACK 机制做保障，所以即使网络断开重连，消息一样不会丢失。

Redis 5.0 版本后，正式发布 Stream 功能，相信是有可能可以替代掉 Redis Pub/Sub 功能，提供可靠的消息订阅功能。

当然，不能说 Redis Pub/Sub 毫无使用的场景，以下艿艿来列举几个：

1、在使用 Redis Sentinel 做高可用时，Jedis 通过 Redis Pub/Sub 功能，实现对 Redis 主节点的故障切换，刷新 Jedis 客户端的主节点的缓存。如果出现 Redis Connection 订阅的异常断开，会重新主动去 Redis Sentinel 的最新主节点信息，从而解决 Redis Pub/Sub 可能因为网络问题，丢失消息。
2、Redis Sentinel 节点之间的部分信息同步，通过 Redis Pub/Sub 订阅发布。
3、在我们实现 Redis 分布式锁时，如果获取不到锁，可以通过 Redis 的 Pub/Sub 订阅锁释放消息，从而实现其它获得不到锁的线程，快速抢占锁。当然，Redis Client 释放锁时，需要 PUBLISH 一条释放锁的消息。在 Redisson 实现分布式锁的源码中，我们可以看到。
4、Dubbo 使用 Redis 作为注册中心时，使用 Redis Pub/Sub 实现注册信息的同步。

限流算法，常用的分成四种：

每一种的概念，推荐看看 《计数器、滑动窗口、漏桶、令牌算法比较和伪代码实现》 文章。

计数器

比较简单，每固定单位一个计数器即可实现。

滑动窗口

Redisson 提供的是基于滑动窗口 RateLimiter 的实现。相比计数器的实现，它的起点不是固定的，而是以开始计数的那个时刻开始为一个窗口。

所以，我们可以把计数器理解成一个滑动窗口的特例，以固定单位为一个窗口。

令牌桶算法

《Eureka 源码解析 —— 基于令牌桶算法的 RateLimiter》 ，单机并发场景下的 RateLimiter 实现。

《Spring-Cloud-Gateway 源码解析 —— 过滤器 (4.10) 之 RequestRateLimiterGatewayFilterFactory 请求限流》 ，基于 Redis 实现的令牌桶算法的 RateLimiter 实现。

漏桶算法

漏桶算法，一直没搞明白和令牌桶算法的区别。现在的理解是：

令牌桶算法，桶里装的是令牌。每次能拿取到令牌，就可以进行访问。并且，令牌会按照速率不断恢复放到令牌桶中直到桶满。
漏桶算法，桶里装的是请求。当桶满了，请求就进不来。例如说，Hystrix 使用线程池或者 Semaphore 信号量，只有在请求未满的时候，才可以进行执行。
上面哔哔了非常多的字，只看本文的话，就那一句话：“Redisson 提供的是基于滑动窗口 RateLimiter 的实现。”。