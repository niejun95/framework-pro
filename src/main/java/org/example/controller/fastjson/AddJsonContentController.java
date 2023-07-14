package org.example.controller.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fastjson")
@Slf4j
public class AddJsonContentController {

    @GetMapping("/add")
    public String addJsonContent() {
        List<IgnoreEntity> list = new ArrayList<>();
        IgnoreEntity entity = new IgnoreEntity();
        entity.setId(1);
        entity.setName("ryan");
        entity.setGender("male");
        entity.setPhone("18156969092");
        list.add(entity);
        IgnoreEntity entity1 = new IgnoreEntity();
        entity1.setGender("famale");
        entity1.setId(2);
        entity1.setName("rose");
        entity1.setPhone("18156969093");
        list.add(entity1);

        JsonContent content = new JsonContent();
        content.setUsername("neijun");
        content.setPassword("123456");
        content.setEntityList(list);
        String firstResult = JSON.toJSONString(content);
        log.info("first result: {}", firstResult);

        JSONObject jsonObject = JSONObject.parseObject(firstResult);


        String entityListStr = JSON.parseObject(firstResult).getString("entityList");
        List<IgnoreEntity> entityList = JSON.parseArray(entityListStr, IgnoreEntity.class);
        IgnoreEntity entity2 = new IgnoreEntity();
        entity2.setId(3);
        entity2.setName("jack");
        entity2.setGender("male");
        entity2.setPhone("18156969094");
        entityList.add(entity2);
        jsonObject.put("entityList", entityList);
        return jsonObject.toString();
    }
}
