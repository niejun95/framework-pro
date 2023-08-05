package org.example.handler.xxl;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.example.service.UserService;
import org.example.utils.XxlUtil;
import org.springframework.stereotype.Component;

/**
 * @author ryan
 * @version 1.0.0
 * @description 用户注册成功，打招呼
 * @date 2023/08/05 11:22:36
 */
@Component
@RequiredArgsConstructor
public class SayHelloHandler {
    private final UserService userService;

    private final XxlUtil xxlUtil;

    @XxlJob(value = "sayHelloHandler")
    public void execute() throws Exception {
        String jobParam = XxlJobHelper.getJobParam();
        userService.sayHelloToUser(jobParam);

        long jobId = XxlJobHelper.getJobId();
        xxlUtil.removeJob(jobId);
    }
}
