package com.baizhi.travels.service;

import com.baizhi.travels.entity.Place;

import java.util.List;

/**
 * @author Liu
 * @date 2020/10/14 21:35:49
 * @description
 */

public interface PlaceService {

    List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId);


    Integer findByProvinceIdCounts(String provinceId);

    void save(Place place);
}
