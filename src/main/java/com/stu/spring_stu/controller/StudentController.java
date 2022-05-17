package com.stu.spring_stu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.stu.spring_stu.service.IStudentService;
import com.stu.spring_stu.entity.Student;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ybh
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;


    @PostMapping
    public boolean save(@RequestBody Student student){
        return studentService.saveOrUpdate(student);
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.list();
    }

    @DeleteMapping("/{stuId}")
    public Boolean delete(@PathVariable Integer stuId){
        return studentService.removeById(stuId);
    }

    @GetMapping("/{stuId}")
    public Student findOne(@PathVariable Integer stu_id) {
        return studentService.getById(stu_id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return studentService.removeByIds(ids);
    }

    @GetMapping("/page")
    public Page<Student> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String stuId,
                                  @RequestParam(defaultValue = "") String name,
                                  @RequestParam(defaultValue = "") String depId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        if(stu_id != null){
//            queryWrapper.like("stu_id",stu_id);
//        }
        queryWrapper.like(Strings.isNotEmpty(stuId),"stu_id",stuId);
        queryWrapper.like(Strings.isNotEmpty(name),"name", name);
        queryWrapper.like(Strings.isNotEmpty(depId),"dep_id",depId);
        return studentService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }

}
