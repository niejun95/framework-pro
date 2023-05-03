package org.example.controller.valid;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/valid")
public class ValidParameterController {


    @PostMapping("/check")
    public void check(@RequestBody @Validated ValidEntity entity) {
      log.info(JSON.toJSONString(entity));
    }
}
