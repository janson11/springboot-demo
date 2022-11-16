package com.janson.netty.common.protocol;

import com.janson.netty.common.util.JsonUtil;
import com.janson.netty.common.util.RandomUtil;
import lombok.Data;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/16 10:36 上午
 */
@Data
public class JsonMsg {

    private int id;

    private String content = "疯狂创客圈：高性能学习社群";

    /**
     * 在通用方法中，使用阿里FastJson转换成Java对象
     */
    public static JsonMsg parseFromJson(String json){
        return JsonUtil.jsonToPojo(json,JsonMsg.class);
    }

    /**
     * 在通用方法中，使用谷歌Gson转换成字符串。
     */
    public String convertToJson(){
        return JsonUtil.pojoToJson(this);
    }

    public JsonMsg(int id){
        this.id = id;
    }

    public JsonMsg(){
        this.id = RandomUtil.randInMod(100);
    }

}
