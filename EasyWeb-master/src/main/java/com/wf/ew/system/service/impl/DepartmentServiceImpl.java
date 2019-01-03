package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wf.ew.common.JsonResult;
import com.wf.ew.system.dao.DepartmentMapper;
import com.wf.ew.system.model.Department;
import com.wf.ew.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public  class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private  DepartmentMapper departmentMapper;

    @Override
    public List<Department> getPageDapartmens(Pagination page,Department departmentName) {
        List<Department> list = departmentMapper.getDepartmentAll(page,departmentName);
        return list;
    }

    @Override
    public JsonResult insertDepartment(Department pram) {
        int counts = departmentMapper.getDepartmentName(pram.getDepartmentName());
        if (counts == 0) {
            pram.setCreateTime(new Date());
            int result = departmentMapper.insert(pram);
            if (result > 0) {
                return JsonResult.ok("添加成功");
            } else {
                return  JsonResult.error("添加失败");
            }
        } else {
            return JsonResult.error("已存在部门");
        }
    }

	@Override
	public JsonResult editDepartment(Department pram) {
		int counts = departmentMapper.getDepartmentName(pram.getDepartmentName());
		if (counts == 0) {
			pram.setUpdateTime(new Date());
			int result = departmentMapper.updateById(pram);
			if (result > 0) {
				return JsonResult.ok("修改成功");
			} else {
				return JsonResult.error("修改失败");
			}
		} 
		return JsonResult.error("修改失败,已存在该部门");
	}

	@Override
	public JsonResult delDepartment(Integer id) {
		// TODO Auto-generated method stub
		int result = departmentMapper.deleteById(id);
		if (result > 0) {
			return JsonResult.ok("部门删除成功");
		} else {
			return JsonResult.error("删除失败");
		}
	}


}
