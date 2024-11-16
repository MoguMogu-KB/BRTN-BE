package com.example.backend.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserVO {
    private String userId; // DB의 `id`
    private String pwd;
    private String name;
    private String gender;
    private Integer age; // null 허용 때문에 Integer
}
