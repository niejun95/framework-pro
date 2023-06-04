package org.example.controller.trans;

import org.example.controller.trans.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestMessController
 * @author niejun
 * @CreateTime: 2022-07-25  15:36
 * @description TestMessController
 * @version 1.0
 */
@RestController
@RequestMapping("/trans")
public class TestTransController {
    
    @Autowired
    private TransService transTest;

    @RequestMapping("/test")
    public void test() {
        transTest.updateData();
    }

}
