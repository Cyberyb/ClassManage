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
@ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("教师号")
    @TableId(value = "tea_id")
      private String teaId;

    @ApiModelProperty("教师名")
    private String tname;

    @ApiModelProperty("学院号")
    private Integer depId;

    @ApiModelProperty("账户名")
    private String username;

    @ApiModelProperty("密码")
    private String passwd;

    @ApiModelProperty("角色（教师/管理员）")
    private String role;

    @TableField(exist = false)
    private String dname;
}
