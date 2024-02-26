package com.janson.springboot.lab17.dynamicdatasource.mapper;

import com.janson.springboot.lab17.dynamicdatasource.daoobject.OrderDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/28 20:17
 */
@Repository
public interface OrderMapper {

    OrderDO selectById(@Param("id") Integer id);

}
