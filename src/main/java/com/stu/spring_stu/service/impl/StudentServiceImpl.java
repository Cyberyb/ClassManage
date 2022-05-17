package com.stu.spring_stu.service.impl;

import com.stu.spring_stu.entity.Student;
import com.stu.spring_stu.mapper.StudentMapper;
import com.stu.spring_stu.service.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
