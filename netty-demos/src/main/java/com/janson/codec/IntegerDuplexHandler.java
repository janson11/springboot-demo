package com.janson.codec;

import com.janson.netty.demos.decoder.Byte2IntegerDecoder;
import com.janson.netty.demos.encoder.Integer2ByteEncoder;
import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * @Description: 保证了有了相反逻辑关系的encoder编码器和decoder解码器，既可以结合使用，又可以分开使用，十分方便。
 * @Author: shanjian
 * @Date: 2022/11/14 10:27 上午
 */
public class IntegerDuplexHandler extends CombinedChannelDuplexHandler<Byte2IntegerDecoder, Integer2ByteEncoder> {
    public IntegerDuplexHandler() {
        super(new Byte2IntegerDecoder(),new Integer2ByteEncoder());
    }
}
