package com.stu.spring_stu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.common.ClassResult;
import com.stu.spring_stu.entity.Electclass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.spring_stu.entity.Openclass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 选课表 Mapper 接口
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Mapper
public interface ElectclassMapper extends BaseMapper<Electclass> {


    Boolean delcourse(String stuId, String couId, String teaId);
    void selectcourse(String stuId, String couId, String teaId);

    void updategrade(String stuId, String couId, String teaId, String cj);

    Page<Electclass> findPage(Page<Electclass> page, String stuId, String couId,String cname, String teaId,String tname,String xq);

    List<Electclass> selectcoursebytime(String stuId,String xq, String time);

    Page<Electclass> findGrades(Page<Electclass> page, String stuId, String couId, String teaId, String xq);

    Integer findNumofStu(String teaId, String couId);

    Integer findNumofGrade(String teaId, String couId);

    Integer findNumofGood(String teaId, String couId);

    Integer findNumofGreat(String teaId, String couId);
}
