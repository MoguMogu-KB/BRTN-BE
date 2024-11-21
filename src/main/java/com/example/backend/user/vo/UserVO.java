package com.example.backend.user.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    private String userId; // DB의 `id`
    private String pwd;
    private String name;
    private String gender;
    private Integer age;
    private Integer warning;// null 허용 때문에 Integer
}
