package com.wf.ew.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wf.ew.system.model.Department;

import java.util.List;

public  interface DepartmentMapper extends BaseMapper<Department> {
    List<Department> getDepartmentAll();
    Integer getDepartmentName(String  departmentName);
}
