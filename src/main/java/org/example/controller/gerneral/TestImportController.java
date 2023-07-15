package org.example.controller.gerneral;

import lombok.extern.slf4j.Slf4j;
import org.example.portim.ImportBeanA;
import org.example.portim.ImportBeanB;
import org.example.portim.ImportBeanC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description import 方式导入bean
 * @author ryan
 * @date 2023/5/9 14:08
 * @version 1.0
 */
@RestController
@RequestMapping("/import")
@Slf4j
public class TestImportController {

    @Resource
    ImportBeanA importBeanA;

    @Resource
    ImportBeanB importBeanB;

    @Resource
    ImportBeanC importBeanC;

    @GetMapping("/simple")
    public void simpleAnnotation() {
        log.info("simple import annotation {}", importBeanA);
        log.info("simple import annotation {}", importBeanB);
        log.info("simple import annotation {}", importBeanC);
    }

}
