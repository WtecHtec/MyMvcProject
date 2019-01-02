package com.wf.ew.system.controller;

import com.wf.ew.common.BaseController;
import com.wf.ew.common.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "获取个人信息", tags = "main")
@Controller
public class MainController extends BaseController {
    @ApiIgnore
    @RequestMapping({"/", "/index"})
    public String index() {
        return "redirect:index.html";
    }

    @ResponseBody
    @ApiOperation(value = "获取个人信息")
    @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "query")
    @GetMapping("${api.version}/userInfo")
    public JsonResult userInfo() {
        return JsonResult.ok().put("user", getLoginUser());
    }
}
