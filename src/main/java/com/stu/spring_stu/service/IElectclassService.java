package com.stu.spring_stu.service;

import com.stu.spring_stu.entity.Electclass;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

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
}
