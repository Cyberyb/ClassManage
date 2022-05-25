package com.stu.spring_stu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.common.ClassResult;
import com.stu.spring_stu.common.Constants;
import com.stu.spring_stu.common.Result;
import com.stu.spring_stu.controller.dto.UserDTO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.awt.*;
import java.util.List;
import java.util.Objects;

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

    @PostMapping("/login")
    public Student login(@RequestBody UserDTO userDTO){   //@RequestBody用于把前端的json对象转化为java对象
        String username = userDTO.getUsername();
        String passwd = userDTO.getPasswd();
//        if(StringUtils.isBlank(username) || StringUtils.isBlank(passwd)){
//            return false;
//        }
        return studentService.login(userDTO);
    }
    @PostMapping("/update/{upstuId}/{upusername}")
    public Result update(@RequestBody Student student,
                         @PathVariable Integer upstuId,
                         @PathVariable String upusername){
        Integer stuId = student.getStuId();
        String username = student.getUsername();
        if(!Objects.equals(username, upusername)){
            QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("username",username);
            int b = (int) studentService.count(queryWrapper1);
            if(b != 0){
                return Result.error(Constants.CODE_603,"账号重复，请更新");
            }
        }
        studentService.saveOrUpdate(student);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Student student){
        Integer stuId = student.getStuId();
        String username = student.getUsername();
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("stu_id",stuId);
        queryWrapper1.eq("username",username);
        int a = (int) studentService.count(queryWrapper); //返回同学号的人数
        int b = (int) studentService.count(queryWrapper1); // 返回同账号的人数
        if (a == 0 && b == 0){
            studentService.save(student);
            return Result.success();
        }
        else if(a != 0)
            return Result.error(Constants.CODE_602,"学号重复，请重新创建");
        else
            return Result.error(Constants.CODE_603,"账号重复，请重新创建");
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
                                  @RequestParam(defaultValue = "") String dname
    ) {
//        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        if(stu_id != null){
//            queryWrapper.like("stu_id",stu_id);
//        }
//        queryWrapper.like(Strings.isNotEmpty(stuId),"stu_id",stuId);
//        queryWrapper.like(Strings.isNotEmpty(name),"name", name);
//        queryWrapper.like(Strings.isNotEmpty(depId),"dep_id",depId);
        return studentService.findPage(new Page<>(pageNum, pageSize),stuId,name,dname);
    }

}
