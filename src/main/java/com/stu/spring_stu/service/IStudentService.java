package com.stu.spring_stu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ybh
 * @since 2022-05-18
 */
public interface IStudentService extends IService<Student> {

    Page<Student> findPage(Page<Student> page, @Param("stuId") String stuId,@Param("name") String name,@Param("dname") String dname);
}
