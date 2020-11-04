package com.baizhi.travels.controller;

import com.baizhi.travels.entity.Place;
import com.baizhi.travels.entity.Result;
import com.baizhi.travels.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liu
 * @date 2020/10/14 21:39:20
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("place")
public class PlaceController {

    @RequestMapping("/hello")
    public String helloSpringBoot() {
        return "Hello, tomcat.";
    }


    @Autowired
    private PlaceService placeService;

    @Value("${upload.dir}")
    private String realPath;

    @PostMapping("save")
    public Result save(MultipartFile pic, Place place) throws IOException {
        System.out.println(pic.getOriginalFilename());
        System.out.println(place);
        Result result = new Result();
        try{

            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            //  base64编码处理
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            //文件上传
            pic.transferTo(new File(realPath,newFileName));


            //保存place对象
            placeService.save(place);
            result.setMsg("保存景点信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }


        return result;
    }

    /**
     * 根据省份id查询景点的方法
     * @param provinceId
     * @return
     */
    @GetMapping("findAllPlace")
    public Map<String, Object> findAllPlace(Integer page, Integer rows, String provinceId){

        HashMap<String, Object> result = new HashMap<>();
        page = page == null ? 1 : page;
        rows = rows == null ? 3 : rows;
        //景点集合
        List<Place> places = placeService.findByProvinceIdPage(page, rows, provinceId);
        //处理分页
        Integer counts = placeService.findByProvinceIdCounts(provinceId);
        //总页数
        Integer totalPage = counts % rows == 0 ? counts / rows : counts / rows + 1;
        result.put("places", places);
        result.put("page", page);
        result.put("counts", counts);
        result.put("totalPage", totalPage);

        return result;
    }

}
