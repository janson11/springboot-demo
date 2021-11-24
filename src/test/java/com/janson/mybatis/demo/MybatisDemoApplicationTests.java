package com.janson.mybatis.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.janson.mybatis.demo.mapper.UserMapper;
import com.janson.mybatis.demo.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MybatisDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void selectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @Test
    void testSelectEq() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", "tom");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testSelectLike() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name", "t");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testSelectBetween() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.between("age", 21, 24);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testSelectOrder() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testSelectLimit() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.last("limit 2");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }


    @Test
    void testSelectId() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
        users.forEach(System.out::println);
    }


    @Test
    void testSelectPage() {
        Page<User> page = new Page<>(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        long pages = userPage.getPages();
        long current = userPage.getCurrent();
        List<User> records = userPage.getRecords();
        long total = userPage.getTotal();
        boolean hasNext = userPage.hasNext();
        boolean hasPrevious = userPage.hasPrevious();
        records.forEach(System.out::println);
        System.out.println("总页数" + pages);
        System.out.println("当前页" + current);
        System.out.println("表中总的记录数" + total);
        System.out.println("是否有下一页" + hasNext);
        System.out.println("是否有上一页" + hasPrevious);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setName("秦始皇1");
        user.setAge(20);
        user.setEmail("test@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setAge(59);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    void testUpdateVersion() {
        User user = userMapper.selectById(1434436645788540933L);
        User user1 = userMapper.selectById(1434436645788540933L);
        user.setAge(1000);
        user1.setAge(2000);
        userMapper.updateById(user);
        userMapper.updateById(user1);
    }


    @Test
    void testDelete() {
        int i = userMapper.deleteById(1434436645788540932L);
        System.out.println(i);
    }

    @Test
    void testDeleteLogic() {
        int i = userMapper.deleteById(1434436645788540931L);
        System.out.println(i);
    }

}
