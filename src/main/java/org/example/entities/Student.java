package org.example.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("student")
@EqualsAndHashCode
public class Student {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String studentName;

    private Integer age;

    private String sex;

    private String email;
}
