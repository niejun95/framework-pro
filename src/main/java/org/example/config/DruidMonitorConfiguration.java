package org.example.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author : niejun
 * @Description: Druid 监控配置
 * @date Date : 2022年06月26日 11:53
 **/
@Configuration
public class DruidMonitorConfiguration {

    /**
     * 配置完成后，监控页面可以启用
     */
    @Bean("druidStatViewServlet")
    public ServletRegistrationBean<StatViewServlet> getDruidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> registrationBean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_USERNAME, "admin");
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_PASSWORD, "123456");
        // 允许重置
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_RESET_ENABLE, "true");
        return registrationBean;
    }

    @Bean
    @DependsOn("webStatFilter")
    public FilterRegistrationBean<WebStatFilter> getDruidWebStatFilter(WebStatFilter webStatFilter) {
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(webStatFilter);
        // 对所有路径进行监控配置
        registrationBean.addUrlPatterns("/*");
        // 路径排除
        registrationBean.addInitParameter(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.gif,*.jpg,*.bmp,*.css," +
                "/druid/*");
        return registrationBean;
    }


    /**
     * 获取 WEB 状态过滤
     */
    @Bean("webStatFilter")
    public WebStatFilter getWebStatFilter() {
        WebStatFilter statFilter = new WebStatFilter();
        // 对 session 状态进行监控
        statFilter.setSessionStatEnable(true);
        return statFilter;
    }


    @Bean("sqlStatFilter")
    public StatFilter getSQLStatFilter(
            @Value("${spring.datasource.druid.filter.stat.merge-sql}")
            boolean mergeSql,
            @Value("${spring.datasource.druid.filter.stat.merge-sql}")
            boolean logSlowSql,
            @Value("${spring.datasource.druid.filter.stat.slow-sql-millis}")
            long slowSqlMillis) {
        // 定义关于SQL监控的处理部分
        StatFilter filter = new StatFilter();
        // 是否需要合并统计
        filter.setMergeSql(mergeSql);
        // 慢 SQL 记录
        filter.setLogSlowSql(logSlowSql);
        // 慢 SQL 执行时间
        filter.setSlowSqlMillis(slowSqlMillis);
        return filter;
    }

    /**
     * 获取 SQL 防火墙
     * @return
     */
    @Bean("sqlWallConfig")
    public WallConfig getSQLWallFilterConfig() {
        // 配置 防火墙
        WallConfig wc = new WallConfig();
        // 允许进行多个 Statement操作（批处理）
        wc.setMultiStatementAllow(true);
        // 不允许执行删除
        wc.setDeleteAllow(true);
        return wc;
    }

    @Bean("sqlWallFilter")
    public WallFilter getSQLWallFilter(WallConfig wallConfig) {
        // 注入防护墙配置项
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }
}
