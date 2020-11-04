package com.baizhi.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Liu
 * @date 2020/10/3 16:43:26
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private String id;
    private String username;
    private String password;
    private String email;
}
