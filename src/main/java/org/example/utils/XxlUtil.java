package org.example.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import org.example.entity.XxlJobInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan
 * @version 1.0.0
 * @description xxl 工具类
 * @date 2023/08/05 11:22:40
 */
@Component
@RequiredArgsConstructor
public class XxlUtil {

    @Value("${xxl.job.admin.address}")
    private String xxlJobAdminAddress;

    private final RestTemplate restTemplate;

    // 请求Url
    private static final String ADD_INFO_URL = "/jobinfo/addJob";
    private static final String REMOVE_INFO_URL = "/jobinfo/removeJob";
    private static final String GET_GROUP_ID = "/jobgroup/loadByAppName";

    /**
     * 添加任务
     */
    public String addJob(XxlJobInfo xxlJobInfo, String appName) {
        Map<String, Object> params = new HashMap<>();
        params.put("appName", appName);
        String json = JSONUtil.toJsonStr(params);
        String result = doPost(xxlJobAdminAddress + GET_GROUP_ID, json);
        JSONObject jsonObject = JSON.parseObject(result);
        Map<String, Object> map = (Map<String, Object>) jsonObject.get("content");
        Integer groupId = (Integer) map.get("id");
        xxlJobInfo.setJobGroup(groupId);
        String xxlJobInfoJson = JSONUtil.toJsonStr(xxlJobInfo);
        return doPost(xxlJobAdminAddress + ADD_INFO_URL, xxlJobInfoJson);
    }

    /**
     * 删除job
     */
    public String removeJob(long jobId) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", String.valueOf(jobId));
        return doPostWithFormData(xxlJobAdminAddress + REMOVE_INFO_URL, map);
    }

    /**
     * 远程调用
     */
    private String doPost(String url, String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        return responseEntity.getBody();
    }

    private String doPostWithFormData(String url, MultiValueMap<String, String> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        return responseEntity.getBody();
    }
}
