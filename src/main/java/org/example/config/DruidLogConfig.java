package org.example.config;

import com.alibaba.druid.filter.logging.Log4j2Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niejun
 * @description Druid 日志配置
 * @date 2022年06月26日 14:06
 **/
@Configuration
public class DruidLogConfig {

    @Bean("logFilter")
    public Log4j2Filter getLogFilter() {
        Log4j2Filter log4j2Filter = new Log4j2Filter();
        // 启用数据库日志
        log4j2Filter.setDataSourceLogEnabled(true);
        // 记录执行日志
        log4j2Filter.setStatementExecutableSqlLogEnable(true);

        return log4j2Filter;
    }
}
