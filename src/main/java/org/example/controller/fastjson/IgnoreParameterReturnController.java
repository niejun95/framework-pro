package org.example.controller.fastjson;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryan
 * @version 1.0.0
 * @description fastjson 忽略实体类中的某个属性不返回
 * @date 2023/06/04 18:52:00
 */
@RestController
@RequestMapping("/ignore")
@Slf4j
public class IgnoreParameterReturnController {

    @GetMapping("/get")
    public IgnoreEntity get() {
        IgnoreEntity entity = new IgnoreEntity();
        entity.setId(1);
        entity.setName("ryan");
        entity.setGender("male");
        entity.setPhone("18156969092");
        log.info("IgnoreEntity : {}", JSON.toJSONString(entity));
        return entity;
    }

}
