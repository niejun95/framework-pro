package org.example.entities;

/**
 * @author : niejun
 * @Description: TODO
 * @date Date : 2022年06月30日 22:04
 **/
public class Area {
    private Integer id;

    private String areaId;

    private String areaName;

    private String parentId;

    private String indexLink;

    private String level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIndexLink() {
        return indexLink;
    }

    public void setIndexLink(String indexLink) {
        this.indexLink = indexLink;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
