package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author niejun
 * @version 1.0
 * @className AnotherUser
 * @createTime 2022-07-28  10:14
 * @description AnotherUser
 */
@Getter
@Setter
public class AnotherUser {

    private Integer id;

    private String name;

    private Date gmtCreate;

    private Date gmtModified;
}
