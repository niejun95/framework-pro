package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entities.ValidUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description 参数校验
 * @author ryan
 * @date 2023/4/27 9:24
 * @version 1.0
 */
@RestController
@RequestMapping("/valid")
@Slf4j
public class TestValidController {

    @PostMapping("/add")
    public void add(@Valid @RequestBody ValidUser user) {
        log.info("[add][validUser: {}]", user);
    }
}
