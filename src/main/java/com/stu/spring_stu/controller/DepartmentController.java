package com.stu.spring_stu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Course;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.stu.spring_stu.service.IDepartmentService;
import com.stu.spring_stu.entity.Department;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 院系表 前端控制器
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private IDepartmentService departmentService;


    @PostMapping
    public boolean save(@RequestBody Department department){
        return departmentService.saveOrUpdate(department);
    }

    @GetMapping
    public List<Department> findAll() {
        return departmentService.list();
    }

    @DeleteMapping("/{dep_id}")
    public Boolean delete(@PathVariable Integer dep_id){
        return departmentService.removeById(dep_id);
    }

    @GetMapping("/{id}")
    public Department findOne(@PathVariable Integer id) {
        return departmentService.getById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return departmentService.removeByIds(ids);
    }

    @GetMapping("/page")
    public Page<Department> findPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam(defaultValue = "") String depId,
                                     @RequestParam(defaultValue = "") String dname) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(depId),"dep_id",depId);
        queryWrapper.like(Strings.isNotEmpty(dname),"dname", dname);
        return departmentService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }
}
