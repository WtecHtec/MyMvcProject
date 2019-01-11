package com.wf.ew.meetingronm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageResult;
import com.wf.ew.meetingronm.controller.EquipmentController;
import com.wf.ew.meetingronm.service.EquipmentService;
import com.wf.ew.system.dao.DepartmentMapper;
import com.wf.ew.system.dao.EquipmentMapper;
import com.wf.ew.system.model.Department;
import com.wf.ew.system.model.Equipment;
import com.wf.ew.system.service.DepartmentService;

@Service
public class EquipmentServiceImpl  extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService{
  
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EquipmentServiceImpl.class);

	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public PageResult<Equipment> getEquipmentAll() {
		log.info("获取设备信息");
		EntityWrapper<Equipment> wrapper = new EntityWrapper<>();
	    List<Equipment> list = equipmentMapper.selectList(wrapper);
	    if (list != null) {
	    	return new  PageResult<>(list);
	    }
		return new  PageResult<>(list);
	}

	@Override
	public JsonResult insertEquipment(Equipment pram) {
		log.info("添加设备信息");
	    int reslut = equipmentMapper.insert(pram);
	    if (reslut > 0) {
	    	return JsonResult.ok("添加设备信息成功");
	    } else {
	    	return JsonResult.error("添加设备信息失败");
	    }
		
	}
	
}
