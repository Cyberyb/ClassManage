package com.stu.spring_stu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Course;
import com.stu.spring_stu.mapper.CourseMapper;
import com.stu.spring_stu.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Page<Course> findPage(Page<Course> page, String couId, String cname, String dname) {
        return courseMapper.findPage(page,couId,cname,dname);
    }
}
