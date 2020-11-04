package com.baizhi.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Liu
 * @date 2020/10/3 19:56:47
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)  //  链式调用
public class Result {
    private Boolean status = true;
    private String msg;
    private String userId;
}
