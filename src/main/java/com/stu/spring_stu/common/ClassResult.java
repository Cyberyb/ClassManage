package com.stu.spring_stu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassResult {
    private Integer sumofstu;//课程总人数
    private Integer gradenum;//课程已经统计分数的人数
    private Integer goodnum;//课程及格人数 cj > 60
    private Integer greatnum;//课程优秀人数 cj > 90
}
