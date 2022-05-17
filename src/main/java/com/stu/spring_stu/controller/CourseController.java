package com.stu.spring_stu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Student;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.stu.spring_stu.service.ICourseService;
import com.stu.spring_stu.entity.Course;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程表 前端控制器
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService courseService;


    @PostMapping
    public boolean save(@RequestBody Course course){
        return courseService.saveOrUpdate(course);
    }

    @GetMapping
    public List<Course> findAll() {
        return courseService.list();
    }

    @DeleteMapping("/{couId}")
    public Boolean delete(@PathVariable Integer couId){
        return courseService.removeById(couId);
    }

    @GetMapping("/{couId}")
    public Course findOne(@PathVariable Integer couId) {
        return courseService.getById(couId);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return courseService.removeByIds(ids);
    }

    @GetMapping("/page")
    public Page<Course> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String couId,
                                 @RequestParam(defaultValue = "") String cname) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(couId),"cou_id",couId);
        queryWrapper.like(Strings.isNotEmpty(cname),"cname", cname);
        return courseService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }

}
