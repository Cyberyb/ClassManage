package com.stu.spring_stu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Openclass;
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
public interface OpenclassMapper extends BaseMapper<Openclass> {

    boolean openclass(String couId, String teaId, String xq, String time, Integer uplim);

    Page<Openclass> findPage(Page<Openclass> page, String couId, String teaId,String cname,String tname,String xq);
}
