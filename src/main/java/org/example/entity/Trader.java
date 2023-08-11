package org.example.entity;

import lombok.Data;

/**
 * @description:
 * @author: ryan
 * @date 2023/6/9 16:07
 * @version: 1.0
 */
@Data
public class Trader {

    private Long id;

    private String name;

    private String phone;

    private String idCard;

    private String bankCard;

    private String address;
}
