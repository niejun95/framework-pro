package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entities.ValidUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @description: 参数校验
 * @author: ryan
 * @date 2023/4/27 9:24
 * @version: 1.0
 */
@RestController
@RequestMapping("/valid")
@Validated // 所有接口都需要进行参数校验
@Slf4j
public class TestValidController {

    @GetMapping("/get")
    public ValidUser get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于0") Integer id) {
        log.info("[get][id:{}]", id);
        ValidUser validUser = new ValidUser();
        validUser.setUsername("张三"); // 用户名只能是字母和数字 但是这里不会报错
        validUser.setPassword("123456");
        return validUser;
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody ValidUser user) {
        log.info("[add][validUser: {}]", user);
    }
}
