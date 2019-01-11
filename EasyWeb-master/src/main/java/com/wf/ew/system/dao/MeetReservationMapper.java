package com.wf.ew.system.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wf.ew.system.model.MeetReservation;
import com.wf.ew.system.model.MeetingRoom;
import com.wf.ew.system.model.ex.MeetReservationEx;

public interface MeetReservationMapper extends BaseMapper<MeetReservation> {

	List<MeetReservationEx> selectMeetReservations(MeetReservationEx pram);
}
