package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entities.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AreaMapper {
    List<Area> queryAllArea();

    List<Area> queryUnderArea(String reg);

    Area queryAreaInfoByName(String areaName);
}
