package com.janson.thread.chapter20;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/1/5 2:31 下午
 */
@Getter
@Setter
public class Person {
    private String name;
    private Integer age;

    @Override
    public int hashCode() {
        return 1;
    }
}
