package com.janson.springboot.lab26.distributedsession.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/13 10:53 AM
 */
@RestController
@RequestMapping("/session")
public class SessionController {


    @GetMapping("/set")
    public void setSession(HttpSession session, @RequestParam("key") String key, @RequestParam("value") String value) {
        session.setAttribute(key, value);
    }

    @GetMapping("/get_all")
    public Map<String, Object> getAllSession(HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();

        for (Enumeration<String> enumeration = session.getAttributeNames(); enumeration.hasMoreElements(); ) {
            String key = enumeration.nextElement();
            Object value = session.getAttribute(key);
            result.put(key, value);
        }
        return result;
    }

}
