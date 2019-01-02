package com.wf.ew.system.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wf.ew.common.BaseController;
import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.exception.BusinessException;
import com.wf.ew.system.model.Role;
import com.wf.ew.system.model.User;
import com.wf.ew.system.model.UserRole;
import com.wf.ew.system.service.RoleService;
import com.wf.ew.system.service.UserRoleService;
import com.wf.ew.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.spring.web.json.Json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "用户相关的接口", tags = "user")
@RestController
@RequestMapping("${api.version}/user")
public class UserController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 这里参数过多，并且参数含有中文，建议用post请求，用restful风格解决不了需求时，建议不要强行使用restful
     * 加了一个/query是避免跟添加用户接口冲突
     */
    @ApiOperation(value = "查询所有用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "limit", value = "每页多少条", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "searchKey", value = "筛选条件字段", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "searchValue", value = "筛选条件关键字", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @RequestMapping("/query")
    public PageResult<User> list(Integer page, Integer limit, String searchKey, String searchValue) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<User> userPage = new Page<>(page, limit);
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            wrapper.eq(searchKey, searchValue);
        }
        wrapper.orderBy("create_time", true);
        userService.selectPage(userPage, wrapper);
        List<User> userList = userPage.getRecords();
        // 关联查询role
        List<Integer> userIds = new ArrayList<>();
        for (User one : userList) {
            userIds.add(one.getUserId());
        }
        List<UserRole> userRoles = userRoleService.selectList(new EntityWrapper().in("user_id", userIds));
        List<Role> roles = roleService.selectList(null);
        for (User one : userList) {
            List<Role> tempUrs = new ArrayList<>();
            for (UserRole ur : userRoles) {
                if (one.getUserId().equals(ur.getUserId())) {
                    for (Role r : roles) {
                        if (ur.getRoleId().equals(r.getRoleId())) {
                            tempUrs.add(r);
                        }
                    }
                }
            }
            one.setRoles(tempUrs);
        }
        return new PageResult<>(userList, userPage.getTotal());
    }

    @ApiOperation(value = "添加用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType = "form"),
            @ApiImplicitParam(name = "roleId", value = "用户角色id，多个用','分割", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PostMapping()
    public JsonResult add(User user, String roleIds) {
        String[] split = roleIds.split(",");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setState(null);
        user.setEmailVerified(null);
        if (userService.insert(user)) {
            List<UserRole> userRoles = new ArrayList<>();
            for (String roleId : split) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setUserId(user.getUserId());
                userRoles.add(userRole);
            }
            if (!userRoleService.insertBatch(userRoles)) {
                throw new BusinessException("添加失败");
            }
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    @ApiOperation(value = "修改用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType = "form"),
            @ApiImplicitParam(name = "roleId", value = "用户角色id", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PutMapping()
    public JsonResult update(User user, String roleIds) {
        String[] split = roleIds.split(",");
        user.setPassword(null);
        user.setState(null);
        user.setEmailVerified(null);
        if (userService.updateById(user)) {
            List<UserRole> userRoles = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            for (String roleId : split) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setUserId(user.getUserId());
                userRoles.add(userRole);
                ids.add(roleId);
            }
            userRoleService.deleteBatchIds(ids);
            if (!userRoleService.insertBatch(userRoles)) {
                throw new BusinessException("修改失败");
            }
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    @ApiOperation(value = "修改用户状态", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "state", value = "状态：0正常，1冻结", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PutMapping("/state")
    public JsonResult updateState(Integer userId, Integer state) {
        if (state == null || (state != 0 && state != 1)) {
            return JsonResult.error("state值需要在[0,1]中");
        }
        User user = new User();
        user.setUserId(userId);
        user.setState(state);
        if (userService.updateById(user)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    @ApiOperation(value = "修改自己密码", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPsw", value = "原密码", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "newPsw", value = "新密码", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PutMapping("/psw")
    public JsonResult updatePsw(String oldPsw, String newPsw) {
        if (!passwordEncoder.matches(oldPsw, getLoginUser().getPassword())) {
            return JsonResult.error("原密码不正确");
        }
        User user = new User();
        user.setUserId(getLoginUserId());
        user.setPassword(passwordEncoder.encode(newPsw));
        if (userService.updateById(user)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    @ApiOperation(value = "重置密码", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PutMapping("/psw/{id}")
    public JsonResult resetPsw(@PathVariable("id") Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode("123456"));
        if (userService.updateById(user)) {
            return JsonResult.ok("重置密码成功");
        }
        return JsonResult.error("重置密码失败");
    }

    @ApiOperation(value = "删除用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id") Integer userId) {
        if (userService.deleteById(userId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }
    @ApiOperation(value = "个人信息修改", notes = "")
    @PostMapping(value = "/updateUser")
    public JsonResult updateUser(User pram) {
        log.info("个人信息修改");
        return  userService.updateUser(pram);
    }

    @ApiOperation(value = "个人信息修改", notes = "")
    @PostMapping(value = "/getInfo/{username}")
    public JsonResult updateUser(@PathVariable("username")String pram) {
        log.info("个人信息修改");
        return  JsonResult.ok().put("user", userService.getByUsername(pram)) ;
    }

}
