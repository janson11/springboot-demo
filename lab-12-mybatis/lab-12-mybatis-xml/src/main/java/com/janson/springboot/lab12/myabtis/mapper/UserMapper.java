package com.janson.springboot.lab12.myabtis.mapper;

import com.janson.springboot.lab12.myabtis.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/1/22 17:44
 */
@Repository
public interface UserMapper {

    int insert(UserDO user);

    int updateById(UserDO user);

    int deleteById(@Param("id") Integer id);

    UserDO selectById(@Param("id") Integer id);

    UserDO selectByUsername(@Param("userName") String userName);

    List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);
}
