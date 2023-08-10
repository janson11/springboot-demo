package com.janson.springboot.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import java.security.DigestException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: user controller
 * @Author: Janson
 * @Date: 2023/7/8 9:19
 **/
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @GetMapping("/log")
    public String log() {
        String phoneNumber = "13665112630";
        String contactPhoneNumber = "13665112631";
        String encryptPhoneNumber = DigestUtils.md5DigestAsHex(phoneNumber.getBytes());
        String encryptContactPhoneNumber = DigestUtils.md5DigestAsHex(contactPhoneNumber.getBytes());
        String operatorId = "123456789";

        log.info("[single] operator id:{} encrypt phoneNumber:{} contact phoneNumber:{}", operatorId, encryptPhoneNumber, encryptContactPhoneNumber);

        List<String> phoneNumbers = new ArrayList<String>(4);
        phoneNumbers.add("13665112632");
        phoneNumbers.add("13665112633");
        phoneNumbers.add("13665112634");
        phoneNumbers.add("13665112635");
        List<String> encryptPhoneNumbers = new ArrayList<String>(4);
        for (String number: phoneNumbers) {
            encryptPhoneNumbers.add(DigestUtils.md5DigestAsHex(number.getBytes()));
        }

        List<String> contactPhoneNumbers = new ArrayList<String>(4);
        contactPhoneNumbers.add("13665112636");
        contactPhoneNumbers.add("13665112637");
        contactPhoneNumbers.add("13665112638");
        contactPhoneNumbers.add("13665112639");

        List<String> encryptContactPhoneNumbers = new ArrayList<String>(4);
        for (String number: contactPhoneNumbers) {
            encryptContactPhoneNumbers.add(DigestUtils.md5DigestAsHex(number.getBytes()));
        }
        log.info("[more] operator id:{} encrypt phoneNumbers:{} contact phoneNumbers:{}", operatorId, encryptPhoneNumbers, encryptContactPhoneNumbers);
        return "success";
    }

    public static void main(String[] args) {


    }


}
