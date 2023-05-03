package org.example.controller.valid;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ValidEntity {

    @NotBlank(message = "内容不能为空")
    @CheckText
    private String content;

}
