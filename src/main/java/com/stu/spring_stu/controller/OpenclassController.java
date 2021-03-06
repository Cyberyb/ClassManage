package com.stu.spring_stu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Department;
import com.stu.spring_stu.entity.Electclass;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.stu.spring_stu.service.IOpenclassService;
import com.stu.spring_stu.entity.Openclass;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@RestController
@RequestMapping("/openclass")
public class OpenclassController {

    @Resource
    private IOpenclassService openclassService;


    @PostMapping
    public boolean save(@RequestBody Openclass openclass){
        return openclassService.saveOrUpdate(openclass);
    }

    @PostMapping("/{couId}/{teaId}/{xq}/{time}/{uplim}")
    public boolean opencourse(@PathVariable String couId,
                              @PathVariable String teaId,
                              @PathVariable String xq,
                              @PathVariable String time,
                              @PathVariable Integer uplim){
        return openclassService.opencouse(couId,teaId,xq,time,uplim);
    }

    @GetMapping
    public List<Openclass> findAll() {
        return openclassService.list();
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id){
        return openclassService.removeById(id);
    }

    //开课表的删除课程项
    @DeleteMapping("/{couId}/{teaId}")
    public Boolean delete(@PathVariable String couId,
                          @PathVariable String teaId){
        QueryWrapper<Openclass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cou_id",couId);
        queryWrapper.eq("tea_id",teaId);
        //ElectclassController.delete(couId,teaId);
        return openclassService.remove(queryWrapper);
    }

    @GetMapping("/{teaId}")
    public List<Openclass> findAll(@PathVariable String teaId) {
        return openclassService.findCoursebyteaId(teaId);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return openclassService.removeByIds(ids);
    }

    @GetMapping("/myclass/{teaId}{couId}")
    public Openclass findCourse(@PathVariable String teaId,
                                @PathVariable String couId){
        QueryWrapper<Openclass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tea_id",teaId);
        queryWrapper.eq("cou_id",couId);
        return openclassService.getOne(queryWrapper);
    }

    @GetMapping("/page")
    public Page<Openclass> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String couId,
                                    @RequestParam(defaultValue = "") String teaId,
                                    @RequestParam(defaultValue = "") String cname,
                                    @RequestParam(defaultValue = "") String tname,
                                    @RequestParam(defaultValue = "") String xq) {
//        QueryWrapper<Openclass> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(Strings.isNotEmpty(couId),"cou_id",couId);
//        queryWrapper.like(Strings.isNotEmpty(teaId),"tea_id",teaId);
        return openclassService.findPage(new Page<>(pageNum, pageSize),couId,teaId,cname,tname,xq);
    }

}
