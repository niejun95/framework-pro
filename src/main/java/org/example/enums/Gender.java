package org.example.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    MALE("男", 1),
    FEMALE("女", 0);

    private String desc;

    private Integer code;

    public static Integer getCode(String desc) {
        for (Gender gender : Gender.values()) {
            if (gender.desc.equals(desc)) {
                return gender.code;
            }
        }
        return null;
    }

    public static String getDesc(Integer code) {
        for (Gender gender : Gender.values()) {
            if (gender.code.equals(code)) {
                return gender.desc;
            }
        }
        return null;
    }
}
