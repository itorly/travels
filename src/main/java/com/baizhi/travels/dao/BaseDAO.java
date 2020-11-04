package com.baizhi.travels.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liu
 * @date 2020/10/9 15:40:35
 * @description
 */
public interface BaseDAO<T,K> {

    void save(T t);

    void update(T t);

    void delete(K k);

    T findOne(K k);

    List<T> findAll();

    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    Integer findTotals();

}
