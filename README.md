# netty

### 1、Channel、Handler、ChannelHandlerContext三者的关系为
Channel通道拥有一条ChannelPipeline通道流水线，每一个流水线节点为一个
ChannelHandlerContext通道处理器上下文对象，每一个上下文中包裹着了一个
ChannelHandler通道处理器。在ChannelHandler通道处理器的入站、出站处理
方法中，Netty都会传递一个Context上下文实例作为实际参数。通过Context实例
的实参，在业务处理中，可以获取ChannelPipeline通道流水线的实例或者Channel
通道的实例。


### 2 、开箱即用的Netty内置Decoder
 ####（1）固定长度数据包解码器——FixedLengthFrameDecoder
适用场景：每个接收到的数据包的长度，都是固定的，例如100个字节。
在这种场景下，只需要把这个解码器加到流水线中，它会把入站ByteBuf数据包拆分成一个个长度为100的数据包，然后发往下一个channelHandler入站处理器。补充说明一下：这里所指的一个数据包，在Netty中就是一个ByteBuf实例。注：数据帧（Frame），本书也通称为数据包。
####（2）行分割数据包解码器——LineBasedFrameDecoder
适用场景：每个ByteBuf数据包，使用换行符（或者回车换行符）作为数据包的边界分割符。
如果每个接收到的数据包，都以换行符/回车换行符作为分隔。在这种场景下，只需要把这个解码器加到流水线中，Netty会使用换行分隔符，把ByteBuf数据包分割成一个一个完整的应用层ByteBuf数据包，再发送到下一站。
####（3）自定义分隔符数据包解码器——DelimiterBasedFrameDecoder
DelimiterBasedFrameDecoder是LineBasedFrameDecoder按照行分割的通用版本。不同之处在于，这个解码器更加灵活，可以自定义分隔符，而不是局限于换行符。如果使用这个解码器，那么接收到的数据包，末尾必须带上对应的分隔符。
####（4）自定义长度数据包解码器——LengthFieldBasedFrameDecoder
这是一种基于灵活长度的解码器。在ByteBuf数据包中，加了一个长度字段，保存了原始数据包的长度。解码的时候，会按照这个长度进行原始数据包的提取。
这种解码器在所有开箱即用解码器中是最为复杂的一种，后面会重点介绍。


具有相互配套逻辑的编码器和解码器能否在同一类中，答案是肯定的，这就要用到Netty的新类型——Codec类型。


# JSON和ProtoBuf序列化
## 1、 评价一个序列化框架的优缺点，大概从两个方面入手
(1)、结果数据大小，原则上说，序列化后的数据尺寸越小，传输效率越高。
(2)、结构复杂度，这会影响序列化/反序列化的效率，结构越复杂，越耗时。
理论上来说说，对于性能要求不是太高的服务器程序，可以选择JSON系列的序列化框架，对于性能要求比较高的服务器程序，则应该选择传输效率更高的二进制序列化框架，目前建议是ProtoBuf

## 2、什么是半包问题？
半包问题包含了"粘包"和"半包"两种情况：
(1)、粘包，指接收端（Receiver）收到了一个ByteBuf,包含了多个发送端(Sender)的Bytebuf,多个ByteBuf"粘"在了一起。
(2)、半包，指接收端（Receiver）将一个发送端的ByteBuf"拆"开了，收到多个破碎的包，换句话说，一个接收端收到的ByteBuf是发送端的一个ByteBuf的一部分。

在Netty中，分包的方法，从第7章可知，主要有两种方法：
（1）可以自定义解码器分包器：基于ByteToMessageDecoder或者ReplayingDecoder，定义自己的进程缓冲区分包器。
（2）使用Netty内置的解码器。如，使用Netty内置的LengthFieldBasedFrameDecoder自定义分隔符数据包解码器，对进程缓冲区ByteBuf进行正确的分包。
在本章后面，这两种方法都会用到。


## Protobuf
Protobuf是Google提出的一种数据交换的格式，是一套类似JSON或者XML的数据传输格式和规范，用于不同应用或进程之间进行通信。
### Protobuf的编码过程为：
使用预先定义的Message数据结构将实际的传输数据进行打包，然后编码成二进制的码流进行传输或者存储 。
### Protobuf的解码过程则刚好与编码过程相反：
将二进制码流解码成Protobuf自己定义的Message结构的POJO实例。
Protobuf既独立于语言，又独立于平台。Google官方提供了多种语言的实现：Java、C#、C++、GO、JavaScript和Python。
Protobuf数据包是一种二进制的格式，相对于文本格式的数据交换（JSON、XML）来说，速度要快很多。由于Protobuf优异的性能，
使得它更加适用于分布式应用场景下的数据通信或者异构环境下的数据交换。

总体来说，在一个需要大量数据传输的应用场景中，因为数据量很大，那么选择Protobuf可以明显地减少传输的数据量和提升网络IO的速度。
对于打造一款高性能的通信服务器来说，Protobuf传输协议是最高性能的传输协议之一。