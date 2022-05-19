package com.stu.spring_stu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
public interface ICourseService extends IService<Course> {

    Page<Course> findPage(Page<Course> page, String couId, String cname, String dname);
}
