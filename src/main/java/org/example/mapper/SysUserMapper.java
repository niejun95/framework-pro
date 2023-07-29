package org.example.mapper;

import org.example.entities.SysUser;

import java.util.List;

public interface SysUserMapper {

    List<SysUser> list();

    int insert(SysUser sysUser);

    int update(SysUser sysUser);
}
