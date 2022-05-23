package com.stu.spring_stu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Electclass;
import com.stu.spring_stu.mapper.ElectclassMapper;
import com.stu.spring_stu.service.IElectclassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 选课表 服务实现类
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Service
public class ElectclassServiceImpl extends ServiceImpl<ElectclassMapper, Electclass> implements IElectclassService {

    @Autowired
    private ElectclassMapper electclassMapper;

    @Transactional
    @Override
    public Electclass selectcourse(String stuId, String couId, String teaId) {
        electclassMapper.delcourse(stuId, couId, teaId);
        electclassMapper.selectcourse(stuId, couId, teaId);
        Electclass electclass = new Electclass();
        return  electclass;
    }

    @Override
    public Boolean deletecourse(String stuId, String couId, String teaId) {
        Boolean res = electclassMapper.delcourse(stuId, couId, teaId);
        System.out.println(res);
        return res;
    }

    @Override
    public Electclass updategrade(String stuId, String couId, String teaId, String cj) {
        electclassMapper.updategrade(stuId,couId,teaId,cj);
        Electclass electclass = new Electclass();
        return electclass;
    }

    @Override
    public Page<Electclass> findPage(Page<Electclass> page, String stuId, String couId,String cname,String teaId,String tname,String xq) {
        return electclassMapper.findPage(page,stuId,couId,cname,teaId,tname,xq);
    }

    @Override
    public List<Electclass> selectcoursebytime(String stuId,String xq, String time) {
        return electclassMapper.selectcoursebytime(stuId,xq,time);
    }

    @Override
    public int removebystuId(QueryWrapper<Electclass> queryWrapper) {
        //使用了Mybatis-plus的Mapper CRUD
        return electclassMapper.delete(queryWrapper);
    }

    @Override
    public Page<Electclass> findGrades(Page<Electclass> page, String stuId, String couId, String teaId, String xq) {
        return electclassMapper.findGrades(page,stuId,couId,teaId,xq);
    }

}
