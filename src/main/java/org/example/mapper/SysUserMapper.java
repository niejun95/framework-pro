package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entities.SysUser;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}