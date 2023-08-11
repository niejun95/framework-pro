package org.example.service.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AopService {
    public void test() {
        log.info("aop service class test method log");
    }
}
