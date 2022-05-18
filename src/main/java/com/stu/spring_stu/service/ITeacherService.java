package com.stu.spring_stu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
public interface ITeacherService extends IService<Teacher> {

    Page<Teacher> findPage(Page<Teacher> page, @Param("teaID") String teaId,@Param("tname") String tname);
}
