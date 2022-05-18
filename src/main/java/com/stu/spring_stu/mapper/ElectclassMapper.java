package com.stu.spring_stu.mapper;

import com.stu.spring_stu.entity.Electclass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.spring_stu.entity.Openclass;
import org.apache.ibatis.annotations.Mapper;

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
}
