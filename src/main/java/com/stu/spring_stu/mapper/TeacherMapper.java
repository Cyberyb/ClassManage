package com.stu.spring_stu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    Page<Teacher> findPage(Page<Teacher> page, String teaId, String tname);
}
