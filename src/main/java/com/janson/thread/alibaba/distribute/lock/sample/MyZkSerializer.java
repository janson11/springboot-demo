package com.janson.thread.alibaba.distribute.lock.sample;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/20 9:23 上午
 */
public class MyZkSerializer implements ZkSerializer {
    @Override
    public byte[] serialize(Object data) throws ZkMarshallingError {
        byte[] bytes = JSON.toJSONBytes(data, SerializerFeature.WriteMapNullValue);
        return bytes;
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        String data = new String(bytes, StandardCharsets.UTF_8);
        return data;
    }
}
