package com.wf.ew.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wf.ew.system.model.Department;

import java.util.List;

public  interface DepartmentMapper extends BaseMapper<Department> {
    List<Department> getDepartmentAll(Pagination page, Department departmentName);
    Integer getDepartmentName(String  departmentName);
}
