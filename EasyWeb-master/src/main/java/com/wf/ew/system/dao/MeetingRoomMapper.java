package com.wf.ew.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wf.ew.system.model.MeetingRoom;
import com.wf.ew.system.model.ex.MeetingRoomEx;

import java.util.List;

public interface MeetingRoomMapper extends BaseMapper<MeetingRoom>  {
     Integer getMeetingRoomName(MeetingRoom  pram);

     List<MeetingRoomEx> getPageMeetingRoom(Pagination page, String meetRoomName);
}
