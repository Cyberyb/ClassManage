package com.stu.spring_stu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Openclass;
import com.stu.spring_stu.mapper.OpenclassMapper;
import com.stu.spring_stu.service.IOpenclassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Service
public class OpenclassServiceImpl extends ServiceImpl<OpenclassMapper, Openclass> implements IOpenclassService {

    @Autowired
    OpenclassMapper openclassMapper;

    @Override
    public boolean opencouse(String couId, String teaId, String xq, String time, Integer uplim) {
        return openclassMapper.openclass(couId,teaId,xq,time,uplim);
    }

    @Override
    public Page<Openclass> findPage(Page<Openclass> page, String couId, String teaId,String xq) {
        return openclassMapper.findPage(page,couId,teaId,xq);
    }
}
