package com.stu.spring_stu.service.impl;

import com.stu.spring_stu.entity.Course;
import com.stu.spring_stu.mapper.CourseMapper;
import com.stu.spring_stu.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

}
