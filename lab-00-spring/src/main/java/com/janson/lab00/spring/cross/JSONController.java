package com.janson.lab00.spring.cross;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2025/1/2 16:54
 **/
@CrossOrigin(maxAge = 3600)
@RequestMapping("/")
@RestController
public class JSONController {

    @GetMapping("/json")
    public Object ob() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "janson");
        return map;
    }
}
