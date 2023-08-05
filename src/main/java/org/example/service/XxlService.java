package org.example.service;

import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.XxlJobInfo;
import org.example.utils.XxlUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ryan
 * @version 1.0.0
 * @description xxl service
 * @date 2023/08/05 11:22:52
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class XxlService {

    private final XxlUtil xxlUtil;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    public void addJob(XxlJobInfo xxlJobInfo) {
        xxlUtil.addJob(xxlJobInfo, appName);
        long triggerNextTime = xxlJobInfo.getTriggerNextTime();
        log.info("任务已添加，将在{}开始执行任务", DateUtil.formatDateTime(new Date(triggerNextTime)));
    }

}
