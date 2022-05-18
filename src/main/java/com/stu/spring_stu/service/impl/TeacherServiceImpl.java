package com.stu.spring_stu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Teacher;
import com.stu.spring_stu.mapper.TeacherMapper;
import com.stu.spring_stu.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Page<Teacher> findPage(Page<Teacher> page, String teaId, String tname) {
        return teacherMapper.findPage(page,teaId,tname);
    }
}
