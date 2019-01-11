package com.wf.ew.meetingronm.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wf.ew.common.BaseController;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.model.User;
import com.wf.ew.system.model.ex.MeetingRoomEx;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wf.ew.common.JsonResult;
import com.wf.ew.meetingronm.service.MeetingRoomService;
import com.wf.ew.system.request.MeetingRoomRequest;

@RestController
@RequestMapping("${api.version}/meetingroom")
public class MeetingRoomController extends BaseController  {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(MeetingRoomController.class);
	
	@Autowired
	private MeetingRoomService meetingRoomService;
	
	@RequestMapping(value="/insertMeetingRoom", method=RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = "application/json")
	public JsonResult insertMeetingRoom(@RequestBody MeetingRoomRequest pram) {
		log.info("添加会议室信息");
		pram.setUserName(getLoginUserName());
		return  meetingRoomService.insertMeetRoom(pram);
	}

	@RequestMapping(value = "/getPageMeetingRoom", method = RequestMethod.POST)
	public PageResult<MeetingRoomEx> getPageMeetingRoom(Integer page, Integer limit, String meetingRoomName) {
		 User user = getLoginUser();
		Pagination pagination = new Pagination(page, limit);
		return  meetingRoomService.getPageMeetingRoom(pagination, meetingRoomName);
	}


	@RequestMapping(value = "/selectRoomEquimentAll", method = RequestMethod.POST)
	public JsonResult selectRoomEquimentAll() {
		return  meetingRoomService.selectRoomEquimentAll();
	} 
	
	@RequestMapping(value = "/delMeetRoomById/{meetId}", method = RequestMethod.POST)
	public JsonResult delMeetRoomById(@PathVariable("meetId")String meetId) {
		
		return  meetingRoomService.delMeetRoomById(Integer.parseInt(meetId));
	}
}
