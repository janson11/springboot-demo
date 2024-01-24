package com.janson.springboot.lab12.myabtis.dataobject;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 用户DO
 * @Author: shanjian
 * @Date: 2023/12/28 20:27
 */
@Data
@Builder
@TableName("users")
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 账号
     */
    private String username;

    private String password;

    private Date createTime;

    /**
     * 是否删除。0-未删除；1-删除
     */
    @TableLogic
    private Integer deleted;
}
