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
