package com.wf.ew.system.controller;

import com.wf.ew.common.JsonResult;
import com.wf.ew.system.model.Department;
import com.wf.ew.system.model.User;
import com.wf.ew.system.service.DepartmentService;
import com.wf.ew.system.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "用户注册接口，不需要验证token")
@RestController
@RequestMapping(value = "/user")
public class ExposureController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/registerUser" )
    public JsonResult registerUser( User parm) {
        log.info("注册用户");
        User user = userService.getByUsername(parm.getUsername());
        if ( user == null) {
            parm.setPassword(passwordEncoder.encode(parm.getPassword()));
            parm.setState(1);
            boolean result = userService.insert(parm);
            if (result == true) return  JsonResult.ok("注册成功，待审核");
            else  return JsonResult.error("注册失败");
        }
        return  JsonResult.error("注册失败，账号已存在");
    }
    @Autowired
    private DepartmentService departmentService;

    // 选择框 显示
//    @PostMapping("/departmentAll")
    @RequestMapping(value = "/departmentAll",method = RequestMethod.POST)
    public JsonResult getDepartmentAll () {
        log.info("获取部门信息，用于下拉框");
        List<Department> list = departmentService.getPageDapartmens();
        if (list != null ) {
            return JsonResult.ok().put("department",list);
        } else {
            return JsonResult.error("获取部门信息失败");
        }
    }
}
