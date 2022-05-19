package com.stu.spring_stu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.spring_stu.entity.Department;
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
    public Boolean delete(@PathVariable Integer id){
        return openclassService.removeById(id);
    }

    @GetMapping("/{id}")
    public Openclass findOne(@PathVariable Integer id) {
        return openclassService.getById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return openclassService.removeByIds(ids);
    }

    @GetMapping("/page")
    public Page<Openclass> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String couId,
                                    @RequestParam(defaultValue = "") String teaId,
                                    @RequestParam(defaultValue = "") String xq) {
//        QueryWrapper<Openclass> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(Strings.isNotEmpty(couId),"cou_id",couId);
//        queryWrapper.like(Strings.isNotEmpty(teaId),"tea_id",teaId);
        return openclassService.findPage(new Page<>(pageNum, pageSize),couId,teaId,xq);
    }

}
