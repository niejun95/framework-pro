package org.example.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoggingServiceImpl implements LoggingService {

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        StringBuilder builder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);
        builder.append("\n");
        builder.append("REQUEST { \n");
        builder.append("    method= ").append(httpServletRequest.getMethod()).append("\n");
        builder.append("    path= ").append(httpServletRequest.getRequestURI()).append("\n");
        //builder.append("    headers= ").append(buildHeadersMap(httpServletRequest)).append("\n");

        if (!parameters.isEmpty()) {
            builder.append("    parameters= ").append(parameters).append("\n");
        }
        if (body != null) {
            builder.append("    body= " + body + "\n");
        }
        builder.append("} \n");

        log.info(builder.toString());
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("RESPONSE { \n");
        builder.append("    method= ").append(httpServletRequest.getMethod()).append("\n");
        builder.append("    path= ").append(httpServletRequest.getRequestURI()).append("\n");
        //builder.append("    responseHeaders= ").append(buildHeadersMap(httpServletResponse)).append("\n");
        builder.append("    responseBody= ").append(body).append("\n");
        builder.append("} \n");
        log.info(builder.toString());
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
