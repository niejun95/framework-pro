package org.example.db;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.SysUser;
import org.example.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void test1() {
        log.info("查询所有数据");
        log.info("列表： {}", JSON.toJSONString(sysUserMapper.selectList(null), JSONWriter.Feature.PrettyFormat));
    }

    @Test
    public void test2() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("ryan");
        sysUser.setRealname("ryan nie");
        sysUserMapper.insert(sysUser);
    }

    @Test
    public void test3() {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setUsername("ryan");
        sysUserMapper.updateById(sysUser);
    }
}
