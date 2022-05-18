package com.stu.spring_stu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ybh
 * @since 2022-05-18
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    Page<Student> findPage(Page<Student> page, String stuId,String name,String dname);
}
