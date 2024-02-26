package com.janson.springboot.lab12.myabtis.mapper;

import com.janson.springboot.lab12.myabtis.MybatisApplication;
import com.janson.springboot.lab12.myabtis.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/1/23 15:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        UserDO user = UserDO.builder().username(UUID.randomUUID().toString()).password("janson4").createTime(new Date()).build();
        userMapper.insert(user);
    }

    @Test
    public void updateById() {
        UserDO updateUser = UserDO.builder().id(4).password("janson411").createTime(new Date()).build();
        userMapper.updateById(updateUser);
    }

    @Test
    public void deleteById() {
        userMapper.deleteById(5);
    }

    @Test
    public void selectById() {
        UserDO userDO = userMapper.selectById(1);
        System.out.println(userDO);
    }

    @Test
    public void selectByUsername() {
        UserDO janson = userMapper.selectByUsername("2952ff68-d117-4af1-9cb2-c5b858ee83fd");
        System.out.println(janson);
    }

    @Test
    public void selectByIds() {
        List<UserDO> userDOS = userMapper.selectByIds(Arrays.asList(1,3));
        System.out.println("users size: " + userDOS.size());
        System.out.println("users: " + userDOS);
    }
}