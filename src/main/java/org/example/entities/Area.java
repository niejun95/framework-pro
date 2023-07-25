package org.example.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * @author niejun
 * @description Area
 * @date 2022年06月30日 22:04
 **/
@Getter
@Setter
public class Area {
    private Integer id;

    private String areaId;

    private String areaName;

    private String parentId;

    private String indexLink;

    private String level;
}
