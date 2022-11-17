package com.janson.netty.demos.protocol;

import com.janson.netty.common.util.Logger;
import org.junit.Test;

/**
 * @Description: 序列化和反序列化Demo
 * @Author: shanjian
 * @Date: 2022/11/16 10:43 上午
 */
public class JsonMsgDemo {

    /**
     * 构建Json对象
     */
    public JsonMsg buildMsg() {
        JsonMsg msg = new JsonMsg();
        msg.setId(1000);
        msg.setContent("疯狂创客圈:高性能学习社群");
        return msg;
    }

    @Test
    public void serAndDser() {
        JsonMsg msg = buildMsg();

        // 将POJO对象，序列化成字符串
        String json = msg.convertToJson();

        // 可以用于网络传输，保存到内存或者外存
        Logger.info("json:="+json);

        // JSON 字符串,反序列化对象POJO
        JsonMsg inMsg = JsonMsg.parseFromJson(json);

        Logger.info("devId:="+inMsg.getId());
        Logger.info("cotent:="+inMsg.getContent());
    }


}
