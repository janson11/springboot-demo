package com.janson.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/11 10:50 上午
 */
public class NacoPasswordDemo {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("nacos"));
        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
//        sCryptPasswordEncoder

//      123456  $2a$10$s/RAwjCv2foDkGfrAW/BZel8W7892lA3u7WmscGKyR4vWi9f7BsTW

        // $2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu

    }
}
