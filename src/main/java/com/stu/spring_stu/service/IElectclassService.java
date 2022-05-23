package com.stu.spring_stu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Electclass;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 选课表 服务类
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
public interface IElectclassService extends IService<Electclass> {

    Electclass selectcourse(@Param("stuId") String stuId, @Param("couId") String couId, @Param("teaId") String teaId);

    Boolean deletecourse(String stuId, String couId, String teaId);

    Electclass updategrade(String stuId, String couId, String teaId, String cj);

    Page<Electclass> findPage(Page<Electclass> page, String stuId, String couId,String cname,String teaId,String tname,String xq);

    List<Electclass> selectcoursebytime(String stuId,String xq, String time);

    int removebystuId(QueryWrapper<Electclass> queryWrapper);

    Page<Electclass> findGrades(Page<Electclass> page, String stuId, String couId, String teaId, String xq);
}
