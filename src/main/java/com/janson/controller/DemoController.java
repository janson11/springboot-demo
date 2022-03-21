package com.janson.controller;

import com.janson.dto.Person;
import com.janson.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/12/27 11:05 上午
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @GetMapping
    public String hello() {
        LocalDateTime now = LocalDateTime.now();
        String result = now.toString() + " hello Zeng Xue Yun";
        log.info("result:{}", result);
        return result;
    }

    @GetMapping("/form")
    public Result<Map<String, Object>> helloFormData(@RequestParam(value = "name") String name, @RequestParam("age") Integer age) {
        Result<Map<String, Object>> result = new Result<>();
        LocalDateTime now = LocalDateTime.now();
        log.info("now: {} , name:{} age :{}", now.toString(), name, age);
        Map map = new HashMap();
        map.put("name", name);
        map.put("age", age);
        map.put("now", now.toString());
        return result.success(map);

    }

    @PostMapping("/json")
    public Result<Person> helloJson(@RequestBody Person person) {
        Result<Person> result = new Result<>();
        LocalDateTime now = LocalDateTime.now();
        if (person == null) {
            throw new IllegalArgumentException("请求体不能为空");
        }
        log.info("now: {} , person :{}", now.toString(), person.toString());
        return result.success(person);

    }


}
