package com.wf.ew.meetingronm.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.model.Equipment;
import com.wf.ew.system.model.MeetingRoom;
import com.wf.ew.system.model.ex.MeetingRoomEx;
import com.wf.ew.system.model.ex.RoomEquimentEx;
import com.wf.ew.system.request.MeetingRoomRequest;

import java.util.List;

public interface MeetingRoomService  extends IService<MeetingRoom> {
	
	JsonResult insertMeetRoom(MeetingRoomRequest pram);

	PageResult<MeetingRoomEx> getPageMeetingRoom(Pagination page, String meetRoomName);

	JsonResult selectRoomEquimentAll();
}
