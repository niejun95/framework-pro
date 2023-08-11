package org.example.controller.gerneral;

import lombok.RequiredArgsConstructor;
import org.example.entity.CommonResult;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryan
 * @version 1.0.0
 * @description xxl controller
 * @date 2023/08/05 11:23:29
 */
@RestController
@RequiredArgsConstructor
public class XxlUserController {

    private final UserService userService;

    @PostMapping("/register")
    public CommonResult register(@RequestBody User user) {
        userService.register(user);
        return CommonResult.success(null);
    }
}
