package com.janson.springboot.lab14.jdbctemplate.dao;

import com.janson.springboot.lab14.jdbctemplate.Application;
import com.janson.springboot.lab14.jdbctemplate.model.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/7/30 17:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsert() {
        UserDO user = new UserDO().setUsername(UUID.randomUUID().toString()).setPassword("nicai").setCreateTime(new Date());
        userDao.insert(user);
        System.out.println(user);
    }

    @Test
    public void insert0() {
        UserDO user = new UserDO().setUsername(UUID.randomUUID().toString()).setPassword("nicai").setCreateTime(new Date());
        userDao.insert0(user);
        System.out.println(user);
    }

    @Test
    public void updateById() {
        UserDO updateUser = new UserDO().setId(1).setPassword("wobucai");
        userDao.updateById(updateUser);
    }

    @Test
    public void deleteById() {
        userDao.deleteById(2);
    }

    @Test
    public void selectById() {
        UserDO user = userDao.selectById(1);
        System.out.println(user);
    }

    @Test
    public void selectByUsername() {
        UserDO userDO = userDao.selectByUsername("janson");
        System.out.println(userDO);
    }

    @Test
    public void selectByIds() {
        List<UserDO> users = userDao.selectByIds(Arrays.asList(1, 3));
        System.out.println("users：" + users.size());
        for (UserDO user : users) {
            System.out.println(user);
        }
    }
}