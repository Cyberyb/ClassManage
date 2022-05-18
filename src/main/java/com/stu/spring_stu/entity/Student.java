package com.stu.spring_stu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
 * @since 2022-05-18
 */
@Getter
@Setter
@ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学号")
      @TableId(value = "stu_id", type = IdType.AUTO)
    private Integer stuId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("院系号")
    private Integer depId;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private Integer passwd;

    @TableField(exist = false)
    private String dname;

}
