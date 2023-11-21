package com.janson.springboot.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2023/11/21 23:39
 **/
@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";
    private static final String DEFAULT_URL = "https://www.baidu.com";


    @RequestMapping(value = ERROR_PATH, produces = MediaType.TEXT_HTML_VALUE)
    public String errorView(HttpServletRequest request) {
        request.setAttribute("url", DEFAULT_URL);
        return this.getDefaulView();
    }

    private String getDefaulView() {
        return "/404";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
