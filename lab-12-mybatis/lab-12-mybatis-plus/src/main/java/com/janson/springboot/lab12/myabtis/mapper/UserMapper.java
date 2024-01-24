package com.janson.springboot.lab12.myabtis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.janson.springboot.lab12.myabtis.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/1/22 17:44
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    default UserDO selectByUsername(@Param("userName") String userName) {
        return selectOne(new QueryWrapper<UserDO>().eq("username", userName));
    }

    List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);

    default IPage<UserDO> selectPageByCreateTime(IPage<UserDO> page, @Param("createTime") Date createTime) {
        return selectPage(page, new QueryWrapper<UserDO>().gt("create_time", createTime));
    }

}
