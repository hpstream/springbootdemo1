/*
 * @copyright 2014-2018 Future-data Inc. All rights reserved.
 */

package com.springbootdemo.controller.api;

import com.google.common.collect.Maps;

import com.github.pagehelper.PageInfo;
import com.springbootdemo.model.User;
import com.springbootdemo.service.UserService;
import com.springbootdemo.util.FormUtil;
import com.springbootdemo.util.JsonUtils;

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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户api.
 *
 * @author kexin.ding
 * @date 2018-06-25 9:32
 **/
@Api(description = "user相关的api")
@RestController
@RequestMapping("api/users")
public class UserApi {

  private final UserService service;
  public UserApi(UserService service) {
    this.service = service;
  }

  /**
   * 获取用户列表.
   *
   * @return 列表
   */
  @ApiOperation(value = "获取用户列表", notes = "查询数据库中用户列表")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "offset", value = "偏移量", paramType = "query"),
          @ApiImplicitParam(name = "limit", value = "限制量", defaultValue = "10", paramType = "query"),
          @ApiImplicitParam(name = "condition", value = "检索条件", paramType = "query")
  })
  @GetMapping
  public PageInfo<User> getUsers(
          HttpServletRequest request,
          @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
          @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
    Map<String,Object> map = FormUtil.getParameterMap(request);

    Map<String,Object> condition = Maps.newHashMap();
    String conditionJson = (String)map.get("condition");
    if (null != conditionJson) {
      condition = JsonUtils.toObject(conditionJson, Map.class);
    }
    return new PageInfo<>(service.getUsers(offset, limit, condition));
  }

  /**
   * 根据id获取用户详情.
   *
   * @param id 用户id
   * @return 详情
   */
  @ApiOperation(value = "根据id获取用户详情", notes = "根据id获取用户详情")
  @ApiImplicitParam(name = "id", value = "user的id", paramType = "path", required = true, dataType = "String")
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
  @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "user的id", paramType = "path", required = true, dataType = "String"),
          @ApiImplicitParam(name = "user", value = "user对象", paramType = "body", required = true, dataType = "User")
  })
  @PutMapping("{id}")
  public String editUserByUserId(
          @PathVariable("id")String id,
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
  @ApiImplicitParam(name = "user", value = "user对象", paramType = "body", required = true, dataType = "User")
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
  @ApiOperation(value = "删除用户", notes = "删除用户")
  @ApiImplicitParam(name = "ids", value = "user的id集合", paramType = "body", required = true, dataType = "List")
  @DeleteMapping
  public String delUserByUserIds(@RequestBody List<String> ids) {
    service.delUserByUserIds(ids);

    return "删除成功";
  }

}
