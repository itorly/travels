package com.baizhi.travels.service;

import com.baizhi.travels.entity.Province;

import java.util.List;

/**
 * @author Liu
 * @date 2020/10/9 16:24:21
 * @description
 */
public interface ProvinceService {

    //  参数1：当前页 //  参数2：每页显示记录
    List<Province> findByPage(Integer page, Integer rows);

    //  查询总条数
    Integer findTotals();

    //保存省份方法
    void save(Province province);

    void delete(String id);

    //查询省份信息
    Province findOne(String id);

    //修改省份信息
    void update(Province province);
}
