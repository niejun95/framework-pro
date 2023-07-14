package org.example.controller.fastjson;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class IgnoreEntity implements Serializable {
    private Integer id;

    private String name;

    private String phone;

    @JSONField(serialize = false)
    private String gender;
}