package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    private Long id;

    private String username;

    private String realname;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
