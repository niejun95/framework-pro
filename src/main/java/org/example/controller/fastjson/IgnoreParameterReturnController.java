package org.example.controller.fastjson;

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
public class IgnoreParameterReturnController {

    @GetMapping
    public IgnoreEntity get() {
        IgnoreEntity entity = new IgnoreEntity();
        entity.setId(1);
        entity.setName("ryan");
        entity.setGender("male");
        entity.setPhone("18156969092");
        return entity;
    }

}
