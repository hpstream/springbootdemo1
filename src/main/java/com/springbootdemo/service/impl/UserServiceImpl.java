/*
 * @copyright 2014-2018 Future-data Inc. All rights reserved.
 */

package com.springbootdemo.service.impl;

import com.springbootdemo.mapper.UserMapper;
import com.springbootdemo.model.User;
import com.springbootdemo.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 用户 业务实现类.
 *
 * @author kexin.ding
 * @date 2018-06-25 10:26
 **/
@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserMapper mapper;


  /**
   * 获取用户列表.
   *
   * @return 列表
   */
  @Override
  public List<User> getUsers() {

    return mapper.getUsers();
  }

  /**
   * 根据id获取用户详情.
   *
   * @param id 用户id
   *
   * @return 详情
   */
  @Override
  public User getUserByUserId(String id) {

    return mapper.getUserByUserId(id);
  }

  /**
   * 根据id修改用户信息.
   *
   * @param user 用户
   */
  @Override
  public void editUserByUserId(User user) {

    mapper.editUserByUserId(user);
  }

  /**
   * 添加用户.
   *
   * @param user 用户
   */
  @Override
  public void addUser(User user) {

    mapper.addUser(user);
  }

  /**
   * 删除用户.
   *
   * @param ids 用户id集合
   */
  @Override
  public void delUserByUserIds(List<String> ids) {

    mapper.delUserByUserIds(ids);
  }
}
