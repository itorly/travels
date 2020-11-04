package com.baizhi.travels.dao;

import com.baizhi.travels.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liu
 * @date 2020/10/14 19:48:06
 * @description
 */
@Mapper
public interface PlaceDAO extends BaseDAO<Place, String>{

    List<Place> findByProvinceIdPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("provinceId") String provinceId);


    Integer findByProvinceIdCounts(String provinceId);

}
