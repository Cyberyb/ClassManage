package com.stu.spring_stu.mapper;

import com.stu.spring_stu.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 院系表 Mapper 接口
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
