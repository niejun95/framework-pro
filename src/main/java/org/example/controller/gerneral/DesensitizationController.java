package org.example.controller.gerneral;

import cn.hutool.core.util.DesensitizedUtil;
import org.example.entities.User;
import org.example.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/add")
    public void add() {
        User user = User.builder()
                .userName("ryan")
                .password("123456")
                .age(18)
                .gender(1)
                .address("上海市浦东新区黄山路1990号")
                .idNo("340827199511206311")
                .build();
        userMapper.insert(user);
    }

    @GetMapping("/get")
    public User get() {
        User user = userMapper.selectById(1L);
        user.setPassword(DesensitizedUtil.password(user.getPassword()));
        user.setIdNo(DesensitizedUtil.idCardNum(user.getIdNo(), 3, 2));
        return user;
    }
}
