package com.janson.springboot.lab17.dynamicdatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.janson.springboot.lab17.dynamicdatasource.constant.DBConstants;
import com.janson.springboot.lab17.dynamicdatasource.daoobject.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/28 20:28
 */
@Repository
@DS(DBConstants.DATASOURCE_USERS)
public interface UserMapper {

    UserDO selectById(@Param("id") Integer id);
}
