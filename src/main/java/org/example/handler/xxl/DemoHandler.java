package org.example.handler.xxl;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ryan
 * @version 1.0.0
 * @description xxl demo
 * @date 2023/08/05 11:22:33
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DemoHandler extends IJobHandler {

    @XxlJob(value = "demoHandler")
    @Override
    public void execute() throws Exception {
        log.info("自动任务" + this.getClass().getSimpleName() + "执行");
    }
}
