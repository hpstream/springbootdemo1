/*
 * @copyright 2014-2018 Future-data Inc. All rights reserved.
 */

package com.springbootdemo.controller.api;

import com.springbootdemo.model.User;
import com.springbootdemo.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

/**
 * 用户api.
 *
 * @author kexin.ding
 * @date 2018-06-25 9:32
 **/
@RestController
@RequestMapping("users")
public class UserApi {

  @Resource
  private UserService service;

  /**
   * 获取用户列表.
   *
   * @return 列表
   */
  @GetMapping
  public List<User> getUsers() {
    return service.getUsers();
  }

  /**
   * 根据id获取用户详情.
   *
   * @param id 用户id
   * @return 详情
   */
  @GetMapping("{id}")
  public User getUserByUserId(@PathVariable("id") String id) {

    return service.getUserByUserId(id);
  }

  /**
   * 根据id修改用户信息.
   *
   * @param user 用户
   * @return 修改结果
   */
  @PutMapping("{id}")
  public String editUserByUserId(@PathVariable("id")String id,
                               @RequestBody User user) {
    user.setId(id);
    service.editUserByUserId(user);

    return "修改成功";
  }

  /**
   * 添加用户.
   *
   * @param user 用户
   * @return 添加结果
   */
  @PostMapping
  public String addUser(@RequestBody User user) {
    service.addUser(user);

    return "添加成功";
  }

  /**
   * 删除用户.
   *
   * @param ids 用户id集合
   * @return 删除结果
   */
  @DeleteMapping
  public String delUserByUserIds(@RequestParam List<String> ids) {
    service.delUserByUserIds(ids);

    return "删除成功";
  }

}
