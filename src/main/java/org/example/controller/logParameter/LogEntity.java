package org.example.controller.logParameter;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

@Component
@RequestScope
@Data
public class LogEntity {
    /**
     * 请求路径
     */
    private String path;

    /**
     * 请求参数
     */
    private Map<String, String[]> params;

    /**
     * 请求体
     */
    private Object req;

    /**
     * 响应体
     */
    private Object resp;
}
