package org.example.controller.fastjson;

import lombok.Data;

import java.util.List;

@Data
public class JsonContent {
    private String username;

    private String password;

    private List<IgnoreEntity> entityList;
}
