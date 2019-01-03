package com.wf.ew.meetingronm.controller;

import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wf.ew.common.BaseController;
import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageResult;
import com.wf.ew.meetingronm.service.EquipmentService;
import com.wf.ew.meetingronm.service.impl.EquipmentServiceImpl;
import com.wf.ew.system.controller.UserController;
import com.wf.ew.system.model.Equipment;

@RestController
@RequestMapping("${api.version}/equipment")
public class EquipmentController extends BaseController {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EquipmentController.class);
	
	@Autowired
	private EquipmentService equipmentService;
	
	@RequestMapping(value="/getEquipmentAll", method=RequestMethod.POST)
	public PageResult<Equipment> getEquipmentAll() {
		log.info("查询所有设备的信息");
		return equipmentService.getEquipmentAll();
	}
	@RequestMapping(value="/insertEquipment", method=RequestMethod.POST)
	public JsonResult insertEquipment(Equipment pram) {
		log.info("查询所有设备的信息");
		pram.setCreateId(getLoginUserName());
		pram.setCreateTime(new Date());
		return equipmentService.insertEquipment(pram);
	}
	
	
}
