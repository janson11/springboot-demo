package com.janson.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/3/18 10:02
 **/
@Data
public class Result<T> {

    private static final String MSG = "请求成功";
    private static final Integer CODE = 200;

    private Integer code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Result(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result<T> success(T data) {
        return new Result<T>(CODE, MSG, data);
    }
}
