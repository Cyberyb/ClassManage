package com.stu.spring_stu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Getter
@Setter
@ApiModel(value = "Course对象", description = "课程表")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程号")
        @TableId(value = "cou_id", type = IdType.AUTO)
      private String couId;

    @ApiModelProperty("课程名")
    private String cname;

    @ApiModelProperty("学分")
    private Integer credit;

    @ApiModelProperty("院系号")
    @TableField(value = "dep_id")
    private Integer depId;

    @TableField(exist = false)
    private String dname;


}
