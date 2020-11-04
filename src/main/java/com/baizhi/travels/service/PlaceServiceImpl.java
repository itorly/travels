package com.baizhi.travels.service;

import com.baizhi.travels.dao.PlaceDAO;
import com.baizhi.travels.entity.Place;
import com.baizhi.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Liu
 * @date 2020/10/14 21:36:39
 * @description
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    private PlaceDAO placeDAO;

    @Autowired
    private ProvinceService provinceService;

    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start = (page-1)*rows;
        return placeDAO.findByProvinceIdPage(start, rows, provinceId);
    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {
        return placeDAO.findByProvinceIdCounts(provinceId);
    }

    @Override
    public void save(Place place) {
        //保存景点，
        placeDAO.save(place);

        Province province = provinceService.findOne(place.getProvinceid());
        province.setPlacecounts(province.getPlacecounts()+1);
        provinceService.update(province);
    }
}
