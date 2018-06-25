/*
 * @copyright 2014-2018 Future-data Inc. All rights reserved.
 */

package com.springbootdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
public class User {

  /**
   * 主键.
   */
  private String id;

  /**
   * 用户名.
   */
  private String userName;

  /**
   * 登录名.
   */
  private String loginName;

  /**
   * 密码.
   */
  private String password;

  /**
   * 备注.
   */
  private String remark;

  /**
   * 创建者.
   */
  private String createUser;

  /**
   * 创建时间.
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  /**
   * 修改者.
   */
  private String updateUser;

  /**
   * 修改时间.
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;

}
