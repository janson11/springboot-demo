package com.janson.jvm.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/6/13 10:29 上午
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    private String name;
    private Integer age;
    private String interest;

}
