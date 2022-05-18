package com.stu.spring_stu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.controller.dto.UserDTO;
import com.stu.spring_stu.entity.Department;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.stu.spring_stu.service.ITeacherService;
import com.stu.spring_stu.entity.Teacher;

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
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private ITeacherService teacherService;

    @PostMapping("/login")
    public Teacher login(@RequestBody UserDTO userDTO){   //@RequestBody用于把前端的json对象转化为java对象
        String username = userDTO.getUsername();
        String passwd = userDTO.getPasswd();
//        if(StringUtils.isBlank(username) || StringUtils.isBlank(passwd)){
//            return false;
//        }
        return teacherService.login(userDTO);
    }

    @PostMapping
    public boolean save(@RequestBody Teacher teacher){
        return teacherService.saveOrUpdate(teacher);
    }

    @GetMapping
    public List<Teacher> findAll() {
        return teacherService.list();
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        return teacherService.removeById(id);
    }

    @GetMapping("/{id}")
    public Teacher findOne(@PathVariable Integer id) {
        return teacherService.getById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return teacherService.removeByIds(ids);
    }

    @GetMapping("/page")
    public Page<Teacher> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String teaId,
                                  @RequestParam(defaultValue = "") String tname) {
//        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(Strings.isNotEmpty(teaId),"tea_id",teaId);
//        queryWrapper.like(Strings.isNotEmpty(tname),"tname", tname);
        return teacherService.findPage(new Page<>(pageNum, pageSize),teaId,tname);
    }

}
