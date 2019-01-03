package com.wf.ew.meetingronm.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wf.ew.common.JsonResult;
import com.wf.ew.meetingronm.controller.EquipmentController;
import com.wf.ew.meetingronm.service.EquipmentService;
import com.wf.ew.meetingronm.service.MeetingRoomService;
import com.wf.ew.system.dao.EquipmentMapper;
import com.wf.ew.system.dao.MeetingRoomMapper;
import com.wf.ew.system.dao.RoomEquimentMapper;
import com.wf.ew.system.model.Equipment;
import com.wf.ew.system.model.MeetingRoom;
import com.wf.ew.system.request.MeetingRoomRequest;

@Service
public class MeetingRoomServiceImpl  extends ServiceImpl<MeetingRoomMapper, MeetingRoom> implements MeetingRoomService {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EquipmentController.class);
	@Autowired
    private MeetingRoomMapper meetingRoomMapper;
	
	@Autowired
	private RoomEquimentMapper roomEquimentMapper;
	
	@Override
	public JsonResult insertMeetRoom(MeetingRoomRequest pram) {
		log.info("会议室信息添加");		
		return null;
	}

}
