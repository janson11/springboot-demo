package com.janson.springboot.labs.lab11.redis.service;

import com.janson.springboot.labs.lab11.redis.cacheobject.UserCacheObject;
import com.janson.springboot.labs.lab11.redis.dao.redis.UserCacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/3/13 15:14
 */
@Service
public class UserService02 {

    @Autowired
    private UserCacheDao userCacheDao;

    public UserCacheObject get(Integer id) {
        return userCacheDao.get(id);
    }

    public void set(Integer id, UserCacheObject object) {
        userCacheDao.set(id, object);
    }

}
