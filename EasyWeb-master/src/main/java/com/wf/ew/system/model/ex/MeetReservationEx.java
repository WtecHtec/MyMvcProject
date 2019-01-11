package com.wf.ew.system.model.ex;

import com.wf.ew.system.model.MeetReservation;

public class MeetReservationEx  extends MeetReservation{


	private String  reservationDateStr;

	private String meetingRoomName;
	
	private String  reservationDateStrStart;
	
	private String  reservationDateStrEnd;
	
	private String reservationPeriodStr;
	
	// 0  自己预约  1 他人
	private Integer mineIs;
	
	
	public String getReservationDateStrStart() {
		return reservationDateStrStart;
	}

	public void setReservationDateStrStart(String reservationDateStrStart) {
		this.reservationDateStrStart = reservationDateStrStart;
	}

	public String getReservationDateStrEnd() {
		return reservationDateStrEnd;
	}

	public void setReservationDateStrEnd(String reservationDateStrEnd) {
		this.reservationDateStrEnd = reservationDateStrEnd;
	}

	public Integer getMineIs() {
		return mineIs;
	}

	public void setMineIs(Integer mineIs) {
		this.mineIs = mineIs;
	}

	public String getMeetingRoomName() {
		return meetingRoomName;
	}

	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
	}

	public String getReservationDateStr() {
		return reservationDateStr;
	}

	public void setReservationDateStr(String reservationDateStr) {
		this.reservationDateStr = reservationDateStr;
	}

	public String getReservationPeriodStr() {
		return reservationPeriodStr;
	}

	public void setReservationPeriodStr(String reservationPeriodStr) {
		this.reservationPeriodStr = reservationPeriodStr;
	}

   
}
