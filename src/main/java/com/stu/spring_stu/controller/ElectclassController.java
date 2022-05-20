package com.stu.spring_stu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.common.Constants;
import com.stu.spring_stu.common.Result;
import com.stu.spring_stu.entity.Department;
import com.stu.spring_stu.entity.Openclass;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.stu.spring_stu.service.IElectclassService;
import com.stu.spring_stu.entity.Electclass;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 选课表 前端控制器
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@RestController
@RequestMapping("/electclass")
public class ElectclassController {

    @Resource
    private IElectclassService electclassService;


    @PostMapping
    public boolean save(@RequestBody Electclass electclass){
        return electclassService.saveOrUpdate(electclass);
    }

    //登录成绩
    @PostMapping("/updategrade/{stuId}/{couId}/{teaId}/{cj}")
    public Electclass updategrade(@PathVariable String stuId,
                               @PathVariable String couId,
                               @PathVariable String teaId,
                               @PathVariable String cj){
        return electclassService.updategrade(stuId,couId,teaId,cj);
    }

    //选课
    @PostMapping("/selectcourse/{stuId}/{couId}/{teaId}/{xq}/{time}")
    public Result selectcourse(@PathVariable String stuId,
                               @PathVariable String couId,
                               @PathVariable String teaId,
                               @PathVariable String xq,
                               @PathVariable String time){
        //先判断时间有无冲突
        List<Electclass> sametimecourse = electclassService.selectcoursebytime(stuId,xq,time);
        System.out.println("查找到时间冲突课程的个数====>");
        System.out.println(sametimecourse.size());
        if(sametimecourse.size() != 0){
            return Result.error(Constants.CODE_600,"选课时间冲突");
        }
        return Result.success(electclassService.selectcourse(stuId,couId,teaId));
    }

    @GetMapping
    public List<Electclass> findAll() {
        return electclassService.list();
    }

    //退课
    @DeleteMapping("/delcourse/{stuId}/{couId}/{teaId}")
    public Boolean delcourse(@PathVariable String stuId,
                             @PathVariable String couId,
                             @PathVariable String teaId){
        return electclassService.deletecourse(stuId,couId,teaId);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        return electclassService.removeById(id);
    }

    //删除某一条指定的选课记录
    @DeleteMapping("/{stuId}/{couId}/{teaId}")
    public Boolean delete(@PathVariable String stuId,
                          @PathVariable String couId,
                          @PathVariable String teaId){
        QueryWrapper<Electclass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",stuId);
        queryWrapper.eq("cou_id",couId);
        queryWrapper.eq("tea_id",teaId);
        queryWrapper.isNull("cj");  //有成绩的不能删除
        return electclassService.remove(queryWrapper);
    }

    //删除指定学生项
    @DeleteMapping("/delstu/{stuId}")
    public Result delete(@PathVariable String stuId){
        QueryWrapper<Electclass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",stuId);
        //queryWrapper.isNull("cj");  //有成绩的不能删除
        int num = electclassService.removebystuId(queryWrapper);
        String msg = "一共删除了该学生的" + num + "条记录";
        System.out.println(msg);
        return Result.success(Constants.CODE_200,msg);
    }

    @GetMapping("/{id}")
    public Electclass findOne(@PathVariable Integer id) {
        return electclassService.getById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return electclassService.removeByIds(ids);
    }

    @GetMapping("/page")
    public Page<Electclass> findPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam(defaultValue = "") String stuId,
                                     @RequestParam(defaultValue = "") String couId,
                                     @RequestParam(defaultValue = "") String teaId) {
//        QueryWrapper<Electclass> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(Strings.isNotEmpty(stuId),"stu_id",stuId);
//        queryWrapper.like(Strings.isNotEmpty(couId),"cou_id",couId);
//        queryWrapper.like(Strings.isNotEmpty(teaId),"tea_id",teaId);
        return electclassService.findPage(new Page<>(pageNum, pageSize),stuId,couId,teaId);
    }

}
