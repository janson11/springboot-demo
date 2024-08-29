package com.janson.springcloud.labx03.feigndemo.consumer;

import feign.Feign;
import feign.Param;
import feign.RequestLine;

/**
 * @Description: 用于展示纯Feign调用的示例
 * @Author: Janson
 * @Date: 2024/8/27 15:54
 **/

interface ProductAPI {
    // 获得商品详情
    @RequestLine("POST /products/{id}")
    String get(@Param("id") Integer id);
}

public class FeignDemo {

    public static void main(String[] args) {
        // 创建ProductAPI对象
        ProductAPI productAPI = Feign.builder().target(ProductAPI.class, "http://localhost:8080");
        // 调用get方法获得商品详情
        String productDetail = productAPI.get(1);
        System.out.println(productDetail);
    }

}
