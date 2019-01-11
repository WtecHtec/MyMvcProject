package com.wf.ew.meetingronm.service;

import com.wf.ew.common.JsonResult;
import com.wf.ew.system.model.MeetReservation;
import com.wf.ew.system.model.ex.MeetReservationEx;

public interface MeetReservationService {

	JsonResult insertMeetReservation(MeetReservation pram);
	
	JsonResult selectMeetReservations(MeetReservationEx pram);
	
	JsonResult selectMeetReservationsByMonth(MeetReservationEx pram);
}
