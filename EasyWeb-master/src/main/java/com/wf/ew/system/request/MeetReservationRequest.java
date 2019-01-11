package com.wf.ew.system.request;

import com.wf.ew.system.model.MeetReservation;

public class MeetReservationRequest extends MeetReservation {

	private String  reservationDateStr;

	public String getReservationDateStr() {
		return reservationDateStr;
	}

	public void setReservationDateStr(String reservationDateStr) {
		this.reservationDateStr = reservationDateStr;
	}


	
}
