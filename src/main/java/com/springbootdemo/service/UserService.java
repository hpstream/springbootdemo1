/*
 * @copyright 2014-2018 Future-data Inc. All rights reserved.
 */

package com.springbootdemo.service;

import com.springbootdemo.model.User;

import java.util.List;
import java.util.Map;

/**
 * 用户业务层.
 *
 * @author kexin.ding
 * @date 2018-06-25 10:25
 **/
public interface UserService {

  /**
   * 获取用户列表.
   *
   * @return 列表
   */
  List<User> getUsers(int offset, int limit, Map<String, Object> condition);

  /**
   * 根据id获取用户详情.
   *
   * @param id 用户id
   * @return 详情
   */
  User getUserByUserId(String id);

  /**
   * 根据id修改用户信息.
   *
   * @param user 用户
   * @return 修改结果
   */
  void editUserByUserId(User user);

  /**
   * 添加用户.
   *
   * @param user 用户
   * @return 添加结果
   */
  void addUser(User user);

  /**
   * 删除用户.
   *
   * @param ids 用户id集合
   * @return 删除结果
   */
  void delUserByUserIds(List<String> ids);

}
