package com.stu.spring_stu.service.impl;

import com.stu.spring_stu.entity.Department;
import com.stu.spring_stu.mapper.DepartmentMapper;
import com.stu.spring_stu.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 院系表 服务实现类
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
