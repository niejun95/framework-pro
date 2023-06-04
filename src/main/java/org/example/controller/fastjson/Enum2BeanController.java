package org.example.controller.fastjson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enum")
public class Enum2BeanController {

    @GetMapping("/listSeason")
    public SeasonEnum[] listSeason() {
        return SeasonEnum.values();
    }
}
