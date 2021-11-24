package com.janson.mybatis.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.janson.mybatis.demo.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: userMapper
 * @Author: Janson
 * @Date: 2021/9/5 12:03
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
