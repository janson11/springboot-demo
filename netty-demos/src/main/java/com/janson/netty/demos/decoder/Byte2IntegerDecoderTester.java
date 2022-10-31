package com.janson.netty.demos.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/31 10:39 上午
 */
public class Byte2IntegerDecoderTester {

    /**
     * 整数解码器的使用示例
     *
     * 2022-10-31 10:45:00.016 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：0
     * 2022-10-31 10:45:00.017 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：0
     * 2022-10-31 10:45:00.017 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：1
     * 2022-10-31 10:45:00.017 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：1
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：2
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：2
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：3
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：3
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：4
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：4
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：5
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：5
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：6
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：6
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：7
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：7
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：8
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：8
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：9
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：9
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：10
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：10
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：11
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：11
     * 2022-10-31 10:45:00.018 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：12
     * 2022-10-31 10:45:00.018 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：12
     * 2022-10-31 10:45:00.019 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：13
     * 2022-10-31 10:45:00.019 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：13
     * 2022-10-31 10:45:00.019 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：14
     * 2022-10-31 10:45:00.019 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：14
     * 2022-10-31 10:45:00.019 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：15
     * 2022-10-31 10:45:00.019 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：15
     * 2022-10-31 10:45:00.019 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：16
     * 2022-10-31 10:45:00.019 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：16
     * 2022-10-31 10:45:00.019 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：17
     * 2022-10-31 10:45:00.020 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：17
     * 2022-10-31 10:45:00.020 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：18
     * 2022-10-31 10:45:00.020 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：18
     * 2022-10-31 10:45:00.020 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：19
     * 2022-10-31 10:45:00.020 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：19
     * 2022-10-31 10:45:00.020 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：20
     * 2022-10-31 10:45:00.020 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：20
     * 2022-10-31 10:45:00.020 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：21
     * 2022-10-31 10:45:00.020 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：21
     * 2022-10-31 10:45:00.020 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：22
     * 2022-10-31 10:45:00.020 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：22
     * 2022-10-31 10:45:00.020 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：23
     * 2022-10-31 10:45:00.020 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：23
     * 2022-10-31 10:45:00.020 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：24
     * 2022-10-31 10:45:00.020 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：24
     * 2022-10-31 10:45:00.020 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：25
     * 2022-10-31 10:45:00.020 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：25
     * 2022-10-31 10:45:00.020 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：26
     * 2022-10-31 10:45:00.021 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：26
     * 2022-10-31 10:45:00.021 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：27
     * 2022-10-31 10:45:00.021 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：27
     * 2022-10-31 10:45:00.021 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：28
     * 2022-10-31 10:45:00.021 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：28
     * 2022-10-31 10:45:00.021 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：29
     * 2022-10-31 10:45:00.022 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：29
     * 2022-10-31 10:45:00.022 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：30
     * 2022-10-31 10:45:00.022 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：30
     * 2022-10-31 10:45:00.022 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：31
     * 2022-10-31 10:45:00.022 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：31
     * 2022-10-31 10:45:00.022 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：32
     * 2022-10-31 10:45:00.022 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：32
     * 2022-10-31 10:45:00.022 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：33
     * 2022-10-31 10:45:00.023 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：33
     * 2022-10-31 10:45:00.023 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：34
     * 2022-10-31 10:45:00.023 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：34
     * 2022-10-31 10:45:00.023 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：35
     * 2022-10-31 10:45:00.023 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：35
     * 2022-10-31 10:45:00.023 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：36
     * 2022-10-31 10:45:00.023 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：36
     * 2022-10-31 10:45:00.023 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：37
     * 2022-10-31 10:45:00.023 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：37
     * 2022-10-31 10:45:00.023 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：38
     * 2022-10-31 10:45:00.023 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：38
     * 2022-10-31 10:45:00.023 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：39
     * 2022-10-31 10:45:00.023 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：39
     * 2022-10-31 10:45:00.023 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：40
     * 2022-10-31 10:45:00.023 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：40
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：41
     * 2022-10-31 10:45:00.024 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：41
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：42
     * 2022-10-31 10:45:00.024 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：42
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：43
     * 2022-10-31 10:45:00.024 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：43
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：44
     * 2022-10-31 10:45:00.024 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：44
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：45
     * 2022-10-31 10:45:00.024 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：45
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：46
     * 2022-10-31 10:45:00.024 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：46
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：47
     * 2022-10-31 10:45:00.024 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：47
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：48
     * 2022-10-31 10:45:00.024 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：48
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：49
     * 2022-10-31 10:45:00.024 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：49
     * 2022-10-31 10:45:00.024 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：50
     * 2022-10-31 10:45:00.025 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：50
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：51
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：51
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：52
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：52
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：53
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：53
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：54
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：54
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：55
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：55
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：56
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：56
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：57
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：57
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：58
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：58
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：59
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：59
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：60
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：60
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：61
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：61
     * 2022-10-31 10:45:00.026 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：62
     * 2022-10-31 10:45:00.026 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：62
     * 2022-10-31 10:45:00.027 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：63
     * 2022-10-31 10:45:00.027 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：63
     * 2022-10-31 10:45:00.027 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：64
     * 2022-10-31 10:45:00.027 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：64
     * 2022-10-31 10:45:00.027 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：65
     * 2022-10-31 10:45:00.027 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：65
     * 2022-10-31 10:45:00.028 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：66
     * 2022-10-31 10:45:00.028 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：66
     * 2022-10-31 10:45:00.029 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：67
     * 2022-10-31 10:45:00.029 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：67
     * 2022-10-31 10:45:00.029 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：68
     * 2022-10-31 10:45:00.029 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：68
     * 2022-10-31 10:45:00.029 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：69
     * 2022-10-31 10:45:00.029 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：69
     * 2022-10-31 10:45:00.029 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：70
     * 2022-10-31 10:45:00.029 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：70
     * 2022-10-31 10:45:00.029 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：71
     * 2022-10-31 10:45:00.029 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：71
     * 2022-10-31 10:45:00.029 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：72
     * 2022-10-31 10:45:00.029 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：72
     * 2022-10-31 10:45:00.029 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：73
     * 2022-10-31 10:45:00.029 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：73
     * 2022-10-31 10:45:00.029 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：74
     * 2022-10-31 10:45:00.029 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：74
     * 2022-10-31 10:45:00.029 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：75
     * 2022-10-31 10:45:00.029 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：75
     * 2022-10-31 10:45:00.030 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：76
     * 2022-10-31 10:45:00.030 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：76
     * 2022-10-31 10:45:00.030 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：77
     * 2022-10-31 10:45:00.030 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：77
     * 2022-10-31 10:45:00.030 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：78
     * 2022-10-31 10:45:00.030 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：78
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：79
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：79
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：80
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：80
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：81
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：81
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：82
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：82
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：83
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：83
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：84
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：84
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：85
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：85
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：86
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：86
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：87
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：87
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：88
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：88
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：89
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：89
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：90
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：90
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：91
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：91
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：92
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：92
     * 2022-10-31 10:45:00.031 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：93
     * 2022-10-31 10:45:00.031 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：93
     * 2022-10-31 10:45:00.032 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：94
     * 2022-10-31 10:45:00.032 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：94
     * 2022-10-31 10:45:00.032 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：95
     * 2022-10-31 10:45:00.032 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：95
     * 2022-10-31 10:45:00.032 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：96
     * 2022-10-31 10:45:00.032 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：96
     * 2022-10-31 10:45:00.032 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：97
     * 2022-10-31 10:45:00.032 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：97
     * 2022-10-31 10:45:00.032 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：98
     * 2022-10-31 10:45:00.032 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：98
     * 2022-10-31 10:45:00.032 [main] INFO  com.janson.netty.demos.decoder.Byte2IntegerDecoder - 解码出一个整数：99
     * 2022-10-31 10:45:00.032 [main] INFO  c.janson.netty.demos.decoder.IntegerProcessHandler - 打印出一个整数：99
     *
     */
    @Test
    public void testByte2IntegerDecoder() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel channel) throws Exception {
                // 请注意先后次序，Byte2IntegerDecoder解码器在前，IntegerProcessHandler整数处理器在后，这是因为入站处理的次序为——从前到后。
                channel.pipeline().addLast(new Byte2IntegerDecoder());
                channel.pipeline().addLast(new IntegerProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 0; i < 100; i++) {
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(i);
            channel.writeInbound(buf);

        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
