package com.stu.spring_stu.service.impl;

import com.stu.spring_stu.entity.Teacher;
import com.stu.spring_stu.mapper.TeacherMapper;
import com.stu.spring_stu.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
