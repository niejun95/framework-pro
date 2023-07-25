package org.example.controller.gerneral;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.Account;
import org.example.entities.CommonResult;
import org.example.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class RequestParamController {
    @Autowired
    AccountMapper mapper;

    @PostMapping("/parameter")
    public void post(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        // 为什么参数注入到了map之后 request中无法获取到了
        log.info("log request param map {}", JSON.toJSONString(request.getParameterMap()));

        Object pageSize1 = request.getAttribute("pageSize");
        log.info("post method page size from request attribute {}", JSON.toJSONString(pageSize1));

        String pageSize = request.getParameter("pageSize");
        log.info("post method page size from request {}", JSON.toJSONString(pageSize));

        log.info("post method parameter map {}", JSON.toJSONString(map));
        log.info("post method page size from map {}", JSON.toJSONString(map.get("pageSize")));
    }

    @GetMapping("/parameter")
    public void get(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        // 为什么参数注入到了map之后 request中无法获取到了
        log.info("log request param map {}", JSON.toJSONString(request.getParameterMap()));

        Object pageSize1 = request.getAttribute("pageSize");
        log.info("get method page size from request attribute {}", JSON.toJSONString(pageSize1));

        String pageSize = request.getParameter("pageSize");
        log.info("get method page size from request {}", JSON.toJSONString(pageSize));

        log.info("get method parameter map {}", JSON.toJSONString(map));
        log.info("get method page size from map {}", JSON.toJSONString(map.get("pageSize")));
    }

    @GetMapping("/all")
    public CommonResult queryAll() {
        List<Account> accounts = mapper.queryAll();
        CommonResult result = new CommonResult();
        result.setCode(200);
        result.setMessage("success");
        result.setData(accounts);
        return result;
    }
}
