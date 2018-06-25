/*
 * @copyright 2014-2018 Future-data Inc. All rights reserved.
 */

package com.springbootdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户.
 *
 * @author kexin.ding
 * @date 2018-06-25 9:46
 **/
@Setter
@Getter
@NoArgsConstructor
@ToString
@ApiModel(value="user对象",description="用户对象user")
public class User {

  /**
   * 主键.
   */
  @ApiModelProperty(value = "主键id", example = "1")
  private String id;

  /**
   * 用户名.
   */
  @ApiModelProperty(value = "用户名", example = "admin")
  private String userName;

  /**
   * 登录名.
   */
  @ApiModelProperty(value = "登录名", example = "admin")
  private String loginName;

  /**
   * 密码.
   */
  @ApiModelProperty(value = "密码", example = "123456")
  private String password;

  /**
   * 备注.
   */
  @ApiModelProperty(value = "备注", example = "无")
  private String remark;

  /**
   * 创建者.
   */
  @ApiModelProperty(value = "创建者", example = "admin")
  private String createUser;

  /**
   * 创建时间.
   */
  @ApiModelProperty(value = "创建时间", example = "2018-02-01")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  /**
   * 修改者.
   */
  @ApiModelProperty(value = "修改者", example = "admin")
  private String updateUser;

  /**
   * 修改时间.
   */
  @ApiModelProperty(value = "修改时间", example = "2018-02-03")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;

}
