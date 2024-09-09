package com.janson.springboot.labs.lab11.redis.dao.redis;

import com.janson.springboot.labs.lab11.redis.cacheobject.UserCacheObject;
import com.janson.springboot.labs.lab11.redis.util.JSONUtil;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/3/13 11:39
 */
@Repository
public class UserCacheDao {

    private static final String KEY_PATTERN = "user:%d";

    @Resource(name = "redisTemplate")
    public ValueOperations<String, String> operations;

    private static String buildKey(Integer id) {
        return String.format(KEY_PATTERN, id);
    }

    public UserCacheObject get(Integer id) {
        String key = buildKey(id);
        String value = operations.get(key);
        return JSONUtil.parseObject(value, UserCacheObject.class);
    }


    public void set(Integer id, UserCacheObject object) {
        String key = buildKey(id);
        String value = JSONUtil.toJSONString(object);
        operations.set(key, value);
    }


}
