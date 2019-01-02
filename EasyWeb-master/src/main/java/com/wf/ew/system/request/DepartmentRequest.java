package com.wf.ew.system.request;

import com.wf.ew.system.model.Department;

public class DepartmentRequest  {

	private Integer page;
	
	private Integer limit;
	
	private String departmentName;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	
}
