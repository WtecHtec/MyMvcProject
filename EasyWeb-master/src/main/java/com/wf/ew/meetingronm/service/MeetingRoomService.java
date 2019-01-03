package com.wf.ew.meetingronm.service;

import com.baomidou.mybatisplus.service.IService;
import com.wf.ew.common.JsonResult;
import com.wf.ew.system.model.Equipment;
import com.wf.ew.system.model.MeetingRoom;
import com.wf.ew.system.request.MeetingRoomRequest;

public interface MeetingRoomService  extends IService<MeetingRoom> {
	
	JsonResult insertMeetRoom(MeetingRoomRequest pram);

}
