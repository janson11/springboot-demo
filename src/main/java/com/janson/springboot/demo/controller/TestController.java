package com.janson.springboot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/8/30 4:47 PM
 */
@RestController
public class TestController {

    @GetMapping("/map")
    public String testLinkedHashMap() {
        try {
            Map<Integer, String> processes = new LinkedHashMap<Integer, String>();
            processes.put(1, "1");
            processes.put(2, "2");
            processes.put(3, "3");

            System.out.println("执行remove前：" + processes.toString());
            Iterator<Integer> iterator = processes.keySet().iterator();
            while (iterator.hasNext()) {
                if (processes.get(iterator.next()) != null) {
                    iterator.remove();
                }
            }
            System.out.println("执行remove后：" + processes.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "sucess";
    }
}
