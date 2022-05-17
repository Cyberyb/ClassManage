package com.stu.spring_stu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 选课表
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Getter
@Setter
@ApiModel(value = "Electclass对象", description = "选课表")
public class Electclass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学号")
    @TableId(value = "stu_id")
      private Integer stuId;

    @ApiModelProperty("课程号")
    @TableField(value = "cou_id")
      private String couId;

    @ApiModelProperty("教师号")
    @TableField(value = "tea_id")
      private String teaId;

    @ApiModelProperty("学期")
    private String xq;

    @ApiModelProperty("课程时间")
    private String time;

    @ApiModelProperty("成绩")
    private Integer cj;


}
