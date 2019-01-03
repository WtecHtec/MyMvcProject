package com.wf.ew.meetingronm.service;

import com.baomidou.mybatisplus.service.IService;
import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.model.Department;
import com.wf.ew.system.model.Equipment;

public interface EquipmentService  extends IService<Equipment> {
     
	//显示设备信息
	PageResult<Equipment> getEquipmentAll();
    // 添加设备信息
	JsonResult insertEquipment(Equipment pram);
}
