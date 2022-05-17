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
 * 院系表
 * </p>
 *
 * @author ybh
 * @since 2022-05-17
 */
@Getter
@Setter
@ApiModel(value = "Department对象", description = "院系表")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("院系号")
    @TableId(value = "dep_id")
    private Integer depId;

    @ApiModelProperty("院系名称")
    private String dname;

    @ApiModelProperty("地址")
    private String adr;

    @ApiModelProperty("联系电话")
    private Integer tel;


}
