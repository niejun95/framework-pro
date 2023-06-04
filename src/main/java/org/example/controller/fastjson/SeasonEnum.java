package org.example.controller.fastjson;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(serializeEnumAsJavaBean = true)
public enum SeasonEnum {
    USER_SCOPE("001", "春"),
    DEPT_SCOPE("002", "夏"),
    ORG_SCOPE("003", "秋"),
    ALLIANCE_SCOPE("004", "冬");
    private String code;
    private String name;

    SeasonEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
