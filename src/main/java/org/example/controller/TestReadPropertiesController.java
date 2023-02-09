package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: TestReadController
 * @Author: ryan
 * @CreateTime: 2022-08-31  22:27
 * @Description: 读取 配置文件中 信息
 * @Version: 1.0
 */
@RestController
@PropertySource(value = {"config.properties", "sql.properties"})
public class TestReadPropertiesController {

    @Value("#{'${data.list}'.split(',')}")
    private List<String> ref;

    @Value("#{'${data.white.list}'.split(',')}")
    private List<String> whiteList;

    @RequestMapping("/read")
    public void readProperties() {
//        System.out.println(ref);
//        List<String> list = new ArrayList<String>();
//        String[] arrs = ref.split(",");
//        for (String arr : arrs) {
//            list.add(arr);
//        }
//        System.out.println(list.size());
        System.out.println(ref);
        System.out.println(whiteList);

    }
}
