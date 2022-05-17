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
 * 
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Getter
@Setter
@ApiModel(value = "Openclass对象", description = "")
public class Openclass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程号")
    @TableId(value = "cou_id")
      private String couId;

    @ApiModelProperty("开课教师号")
    @TableField(value = "tea_Id")
      private String teaId;

    @ApiModelProperty("开课学期(秋、冬、春)")
    private String xq;

    @ApiModelProperty("开课时间（星期几-节数）")
    private String time;

    @ApiModelProperty("选课人数")
    private Integer stunum;

    @ApiModelProperty("选课人数上限")
    private Integer uplim;


}
