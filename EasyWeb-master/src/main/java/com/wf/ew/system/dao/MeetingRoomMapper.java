package com.wf.ew.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wf.ew.system.model.MeetingRoom;

public interface MeetingRoomMapper extends BaseMapper<MeetingRoom>  {
     Integer getMeetingRoomName(MeetingRoom  pram);
}
