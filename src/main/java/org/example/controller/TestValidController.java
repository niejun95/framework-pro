package org.example.controller;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.ValidUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ryan
 * @version 1.0
 * @description 参数校验
 * @date 2023/4/27 9:24
 */
@RestController
@RequestMapping("/valid")
@Slf4j
@Validated
public class TestValidController {

    @PostMapping("/add")
    public void add(@Valid @RequestBody ValidUser user) {
        log.info("[add][validUser: {}]", user);
    }

    @PostMapping("/list")
    public void list(@Valid @RequestBody List<ValidUser> list) {
        // 校验 list 需要类上加 @Validated
        log.info("list info {}", JSON.toJSONString(list));
    }
}
