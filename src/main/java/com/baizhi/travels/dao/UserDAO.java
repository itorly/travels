package com.baizhi.travels.dao;

import com.baizhi.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Liu
 * @date 2020/10/3 17:02:42
 * @description
 */
@Mapper
public interface UserDAO {

    //  query user according to username
    User findByUsername(String username);
    //  注册用户
    void save(User user);
}
