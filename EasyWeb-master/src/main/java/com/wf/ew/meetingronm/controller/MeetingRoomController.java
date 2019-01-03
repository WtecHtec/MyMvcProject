package com.wf.ew.meetingronm.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wf.ew.common.JsonResult;
import com.wf.ew.meetingronm.service.MeetingRoomService;
import com.wf.ew.system.request.MeetingRoomRequest;

@RestController
@RequestMapping("${api.version}/meetingroom")
public class MeetingRoomController {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EquipmentController.class);
	
	@Autowired
	private MeetingRoomService meetingRoomService;
	
	@RequestMapping(value="/insertMeetingRoom", method=RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json")
	public JsonResult insertMeetingRoom(@RequestBody MeetingRoomRequest pram) {
		log.info("添加会议室信息");
		return  meetingRoomService.insertMeetRoom(pram);
	}
}
