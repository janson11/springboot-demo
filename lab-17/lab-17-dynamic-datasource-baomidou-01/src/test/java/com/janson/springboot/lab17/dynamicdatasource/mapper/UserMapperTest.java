package com.janson.springboot.lab17.dynamicdatasource.mapper;

import com.janson.springboot.lab17.dynamicdatasource.DynamicDatasourceApplication;
import com.janson.springboot.lab17.dynamicdatasource.daoobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/29 11:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicDatasourceApplication.class)
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectById() {
        UserDO user = userMapper.selectById(1);
        System.out.println(user);
    }
}