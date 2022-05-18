package com.stu.spring_stu.controller.dto;

import lombok.Data;
//接收前端传入的用户名和密码的数据
@Data
public class UserDTO {
    private String username;
    private String passwd;
}
