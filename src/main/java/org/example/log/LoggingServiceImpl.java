package org.example.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.CommonConstants;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Service
public class LoggingServiceImpl implements LoggingService {

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        Map<String, Object> requestLog = new LinkedHashMap<>();
        Map<String, Object> requestMap = new LinkedHashMap<>();

        Map<String, String> parameters = buildParametersMap(httpServletRequest);
        requestMap.put("method", httpServletRequest.getMethod().toString());
        requestMap.put("path", httpServletRequest.getRequestURI().toString());
        requestMap.put("traceId", MDC.get(CommonConstants.TRACE_ID));
        if (!parameters.isEmpty()) {
            requestMap.put("requestParameters", parameters);
        }
        requestMap.put("requestHeaders", buildHeadersMap(httpServletRequest));
        if (body != null) {
            requestMap.put("requestBody", body);
        }
        requestLog.put("请求报文", requestMap);

        log.info(JSON.toJSONString(requestLog, SerializerFeature.PrettyFormat));
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        Map<String, Object> responseLog = new LinkedHashMap<>();
        Map<String, Object> responseMap = new LinkedHashMap<>();

        responseMap.put("method", httpServletRequest.getMethod().toString());
        responseMap.put("path", httpServletRequest.getRequestURI().toString());
        responseMap.put("traceId", MDC.get(CommonConstants.TRACE_ID));
        responseMap.put("responseHeaders", buildHeadersMap(httpServletResponse));
        responseMap.put("responseBody", body);
        responseLog.put("响应报文", responseMap);

        log.info(JSON.toJSONString(responseLog, SerializerFeature.PrettyFormat));
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }
        return map;
    }
}
