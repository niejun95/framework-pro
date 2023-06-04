package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.entities.Area;
import org.example.mapper.AreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : niejun
 * @description 树形结构 数据库查询
 * @date Date : 2022年06月30日 22:03
 **/
@RequestMapping
@RestController
public class TestDBTreeController {

    @Autowired
    private AreaMapper areaMapper;

    @RequestMapping("/queryAllArea")
    public String queryAllArea() {
        List<Area> areaList = areaMapper.queryAllArea();
        return JSONObject.toJSONString(areaList);
    }

    @RequestMapping("/queryUnderAreaByName/{areaName}")
    public String queryUnderAreaByName(@PathVariable String areaName) {
        Area area = areaMapper.queryAreaInfoByName(areaName);
        List<Area> areaList = areaMapper.queryUnderArea(area.getIndexLink() + "%");
        return JSONObject.toJSONString(areaList);
    }
}
