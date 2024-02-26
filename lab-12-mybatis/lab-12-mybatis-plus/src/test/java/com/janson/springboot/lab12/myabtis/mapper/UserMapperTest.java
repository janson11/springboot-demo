package com.janson.springboot.lab12.myabtis.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.janson.springboot.lab12.myabtis.MybatisApplication;
import com.janson.springboot.lab12.myabtis.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
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
        UserDO user = UserDO.builder().username(UUID.randomUUID().toString()).password("janson8").createTime(new Date()).deleted(0).build();
        userMapper.insert(user);
    }

    @Test
    public void updateById() {
        UserDO updateUser = UserDO.builder().id(6).password("janson7").createTime(new Date()).build();
        userMapper.updateById(updateUser);
    }

    @Test
    public void deleteById() {
        userMapper.deleteById(9);
    }

    @Test
    public void selectById() {
        UserDO userDO = userMapper.selectById(8);
        System.out.println(userDO);
    }

    @Test
    public void selectByUsername() {
        UserDO janson = userMapper.selectByUsername("1bf309cc-23ce-4c5b-b74f-ed5aeb0a003f");
        System.out.println(janson);
    }

    @Test
    public void selectByIds() {
        List<UserDO> userDOS = userMapper.selectByIds(Arrays.asList(1, 3));
        System.out.println("users size: " + userDOS.size());
        System.out.println("users: " + userDOS);
    }

    @Test
    public void testSelectPageByCreateTime() {
        IPage<UserDO> page = new Page<>(1, 10);
        Date createTime = new Date(124, Calendar.JANUARY, 23);
        page = userMapper.selectPageByCreateTime(page, createTime);
        System.out.println("page size: " + page.getSize());
        System.out.println("Current size: " + page.getCurrent());
        System.out.println("total: " + page.getTotal());
        System.out.println("getCurrent: " + page.getCurrent());


    }
}