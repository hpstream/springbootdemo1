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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户api.
 *
 * @author kexin.ding
 * @date 2018-06-25 9:32
 **/
@RestController
@RequestMapping("api/users")
@Api(description = "user相关的api")
public class UserApi {

  @Resource
  private UserService service;

  /**
   * 获取用户列表.
   *
   * @return 列表
   */
  @ApiOperation(value = "获取用户列表", notes = "查询数据库中用户列表")
  @GetMapping
  public @ApiParam(name="用户列表",value="数组", required=true) List<User> getUsers() {
    return service.getUsers();
  }

  /**
   * 根据id获取用户详情.
   *
   * @param id 用户id
   * @return 详情
   */
  @ApiOperation(value = "根据id获取用户详情", notes = "根据id获取用户详情")
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
  @ApiOperation(value = "根据id修改用户信息", notes = "根据id修改用户信息")
  @PutMapping("{id}")
  public String editUserByUserId(
          @ApiParam(name="用户id", value="字符串", required=true)
          @PathVariable("id")String id,
          @ApiParam(name="用户", value="对象", required=true)
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
  @ApiOperation(value = "添加用户", notes = "添加用户")
  @PostMapping
  public String addUser(
          @ApiParam(name="用户对象",value="传入json格式",required=true)
          @RequestBody User user) {
    service.addUser(user);

    return "添加成功";
  }

  /**
   * 删除用户.
   *
   * @param ids 用户id集合
   * @return 删除结果
   */
  @ApiOperation(value = "删除用户", notes = "删除用户")
  @DeleteMapping
  public String delUserByUserIds(@RequestParam List<String> ids) {
    service.delUserByUserIds(ids);

    return "删除成功";
  }

}
