package com.baizhi.travels.service;

import com.baizhi.travels.entity.User;

/**
 * @author Liu
 * @date 2020/10/3 18:49:17
 * @description
 */
public interface UserService {

    User login(User user);

    void register(User user);
}
