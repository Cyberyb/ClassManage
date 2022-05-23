package com.stu.spring_stu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Openclass;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
public interface IOpenclassService extends IService<Openclass> {

    boolean opencouse(String couId, String teaId, String xq, String time, Integer uplim);

    Page<Openclass> findPage(Page<Openclass> page, String couId, String teaId,String cname,String tname,String xq);
}
