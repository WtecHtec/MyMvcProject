package com.wf.ew.meetingronm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.model.Department;
import com.wf.ew.system.model.RoomEquiment;
import com.wf.ew.system.model.ex.MeetingRoomEx;
import com.wf.ew.system.model.ex.RoomEquimentEx;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MeetingRoomServiceImpl  extends ServiceImpl<MeetingRoomMapper, MeetingRoom> implements MeetingRoomService {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(MeetingRoomServiceImpl.class);

	@Autowired
    private MeetingRoomMapper meetingRoomMapper;
	
	@Autowired
	private RoomEquimentMapper roomEquimentMapper;
	
	@Override
	public JsonResult insertMeetRoom(MeetingRoomRequest pram) {
		log.info("会议室信息添加");
		MeetingRoom meetingRoom =  new MeetingRoom();
		meetingRoom.setMeetingRoomName(pram.getMeetingRoomName());
		Integer intCheck =meetingRoomMapper.getMeetingRoomName(meetingRoom);
		meetingRoom.setCreateTime(new Date());
		meetingRoom.setCreateId(pram.getUserName());
		meetingRoom.setMeetingRoomPeoples(pram.getMeetingRoomPeoples());

		if (intCheck == null) {
			int intInsert = meetingRoomMapper.insert(meetingRoom);
			 if (intInsert > 0) {
				 intCheck =meetingRoomMapper.getMeetingRoomName(meetingRoom);
                 if (intCheck != null) {
					for (int i = 0; i < pram.getRoomEquiments().size(); i++ ) {
                 		if (pram.getRoomEquiments().get(i).getEquimentId() == null ) {
                 			pram.getRoomEquiments().remove(i);
                 			i--;
						} else {
							pram.getRoomEquiments().get(i).setCreateId(pram.getUserName());
							pram.getRoomEquiments().get(i).setCreateTime(new Date());
							pram.getRoomEquiments().get(i).setMeetroomId(intCheck);
						}
					}
					Integer result = roomEquimentMapper.insertRoomEquimentList(pram.getRoomEquiments());
                 	if (result != null && result > 0) {
                 		return JsonResult.ok("会议室信息添加成功");
					}
				 }
				 return JsonResult.error("会议室信息添加失败");
			 } else {
				 return JsonResult.error("会议室信息添加失败");
			 }
		}else {
			return JsonResult.error("会议室信息添加失败,已存在该名称的会议室");
		}
	}

	@Override
	public PageResult<MeetingRoomEx> getPageMeetingRoom(Pagination page, String meetRoomName) {
	   Page<MeetingRoomEx> meetingRoomExPage = new Page<>( (int)page.getPages(),page.getLimit());

	   meetingRoomExPage.setRecords( meetingRoomMapper.getPageMeetingRoom(meetingRoomExPage,meetRoomName) );
		List<MeetingRoomEx> result = meetingRoomExPage.getRecords();

		return new PageResult<>(result, meetingRoomExPage.getTotal());
	}

	@Override
	public JsonResult selectRoomEquimentAll() {
		log.info("获取预约会议室页面中会议室信息开始");
		List<RoomEquimentEx> data =  new ArrayList<>();
		data = roomEquimentMapper.selectRoomEquimentAll();
		log.info("获取预约会议室页面中会议室信息结束");
		if (data != null  && data.size() > 0) {
			return JsonResult.ok("获取会议室信息成功").put("data", data);
		}
		return JsonResult.error("获取会议室信息失败");
	}

	@Override
	public JsonResult delMeetRoomById(Integer meetId) {
		log.info("删除会议室信息开始" + meetId);
		int result = meetingRoomMapper.deleteById(meetId);
		if (result > 0) {
			EntityWrapper<RoomEquiment> wrapper = new EntityWrapper<>();
			wrapper.eq("meetroom_id", meetId);
			roomEquimentMapper.delete(wrapper);
			return JsonResult.ok("删除成功");
		}
		return JsonResult.error("删除失败");
	}

}
