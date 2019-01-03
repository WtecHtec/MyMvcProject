package com.wf.ew.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wf.ew.common.BaseController;
import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.model.Department;
import com.wf.ew.system.model.User;
import com.wf.ew.system.request.DepartmentRequest;
import com.wf.ew.system.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/department")
public class DepartmentController  extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DepartmentService departmentService;

    // 添加部门 信息
    @PostMapping("/insertDepartment")
    public JsonResult insertDepartment (Department pram) {
        log.info("添加部门信息");
        pram.setCreateId(getLoginUserName());
        return departmentService.insertDepartment(pram);
    }

    // 查询所有部门 ， 分页显示
    @RequestMapping(value="/pageDepartment", method = RequestMethod.POST)
    public PageResult<Department>  pageDepartment(Integer page, Integer limit,Department pram) {
    	log.info("所有部门，分页显示");
    	 if (page == null) {
             page = 0;
         }
         if (limit == null) {
             limit = 10;
         }
//    	// 设置分页
//    	Page<Department> departmentPage = new Page<>(page, limit);
//    	EntityWrapper<Department> wrapper = new EntityWrapper<>();
//    	if (pram.getDepartmentName() != null && !"".equals(pram.getDepartmentName())) {
//    		wrapper.like("department_name", pram.getDepartmentName());
//    	}
//    	departmentService.selectPage(departmentPage, wrapper);
//    	List<Department> departmentList =  new ArrayList<>();
//    	departmentList = departmentPage.getRecords();
         // 设置分页
         Page<Department> departmentPage = new Page<>(page, limit);
         // 设置数据
        departmentPage.setRecords(departmentService.getPageDapartmens(departmentPage, pram));
        List<Department> departmentList =  new ArrayList<>();
        // 获取数据
        departmentList = departmentPage.getRecords();
        // 返数据
    	return  new PageResult<>(departmentList, departmentPage.getTotal());
    }
    
    // 修改部门信息
    @RequestMapping(value="/editDepartment", method = RequestMethod.POST)
    public JsonResult editDepartment(Department pram) {
    	log.info("修改部门信息");
    	pram.setUpdateId(getLoginUserName());
    	return departmentService.editDepartment(pram);
    }
    // 删除部门
    @RequestMapping(value="/delDepartment/{id}", method = RequestMethod.POST)
    public JsonResult delDepartment(@PathVariable("id") Integer pram) {
    	log.info("删除部门信息");
    	return departmentService.delDepartment(pram);
    }
    
}
