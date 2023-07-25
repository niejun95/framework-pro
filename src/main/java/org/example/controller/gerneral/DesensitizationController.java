package org.example.controller.gerneral;

import cn.hutool.core.util.DesensitizedUtil;
import org.example.entities.User;
import org.example.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

/**
 * @author ryan
 * @version 1.0.0
 * @description 数据脱敏 controller
 * @date 2023/07/25 14:22:06
 */
@RestController
@RequestMapping("/desensitization")
public class DesensitizationController {

    private final UserMapper userMapper;

    public DesensitizationController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userMapper.insert(user);
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable Long id) {
        User user = userMapper.selectById(id);
        user.setPassword(DesensitizedUtil.password(user.getPassword()));
        user.setIdNo(DesensitizedUtil.idCardNum(user.getIdNo(), 3, 2));
        return user;
    }
}
