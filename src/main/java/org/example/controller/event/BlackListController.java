package org.example.controller.event;

import org.example.entity.CommonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackList")
public class BlackListController {

    private final BlackListService blackListService;

    public BlackListController(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    @PostMapping("/query/{phone}")
    public CommonResult query(@PathVariable String phone) {
        blackListService.handle(phone);
        return CommonResult.success(null);
    }
}
