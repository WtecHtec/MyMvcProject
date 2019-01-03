package com.wf.ew.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
import com.wf.ew.common.JsonResult;
import com.wf.ew.system.model.Department;

import java.util.List;

public interface DepartmentService  extends IService<Department> {
    // 获取所有的部门
    List<Department> getPageDapartmens(Pagination page, Department departmentName);
    //添加部门
    JsonResult insertDepartment(Department pram);
    // 修改部门信息
    JsonResult editDepartment(Department pram);
    // 删除
    JsonResult delDepartment(Integer id);
}
