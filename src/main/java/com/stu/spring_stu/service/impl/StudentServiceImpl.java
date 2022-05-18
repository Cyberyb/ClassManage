package com.stu.spring_stu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.controller.dto.UserDTO;
import com.stu.spring_stu.entity.Student;
import com.stu.spring_stu.entity.Teacher;
import com.stu.spring_stu.mapper.StudentMapper;
import com.stu.spring_stu.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ybh
 * @since 2022-05-18
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Page<Student> findPage(Page<Student> page, String stuId,String name,String dname) {
        return studentMapper.findPage(page,stuId,name,dname);
    }

    @Override
    public Student login(UserDTO userDTO) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("passwd",userDTO.getPasswd());
        Student one = getOne(queryWrapper);
        return one;
    }
}
