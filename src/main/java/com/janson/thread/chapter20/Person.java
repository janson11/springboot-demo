package com.janson.thread.chapter20;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/1/5 2:31 下午
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private Integer age;

}
