/*
 * @copyright 2014-2018 Future-data Inc. All rights reserved.
 */

package com.springbootdemo.mapper;

import com.springbootdemo.model.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户 mapper映射.
 *
 * @author kexin.ding
 * @date 2018-06-25 10:17
 **/
@Repository
public interface UserMapper {

  /**
   * 获取用户列表.
   *
   * @return 列表
   */
  List<User> getUsers();

  /**
   * 根据id获取用户详情.
   *
   * @param id 用户id
   * @return 详情
   */
  User getUserByUserId(@Param("id") String id);

  /**
   * 根据id修改用户信息.
   *
   * @param user 用户
   * @return 修改结果
   */
  int editUserByUserId(User user);

  /**
   * 添加用户.
   *
   * @param user 用户
   * @return 添加结果
   */
  int addUser(User user);

  /**
   * 删除用户.
   *
   * @param ids 用户id集合
   * @return 删除结果
   */
  int delUserByUserIds(@Param("ids")List<String> ids);

}
