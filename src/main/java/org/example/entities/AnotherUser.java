package org.example.entities;

import java.util.Date;

/**
 * @className AnotherUser
 * @author niejun
 * @createTime 2022-07-28  10:14
 * @description AnotherUser
 * @version 1.0
 */
public class AnotherUser {

    private Integer id;

    private String name;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
