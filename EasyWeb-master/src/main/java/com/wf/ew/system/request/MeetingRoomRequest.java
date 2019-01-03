package com.wf.ew.system.request;

import java.util.List;

import com.wf.ew.system.model.RoomEquiment;

public class MeetingRoomRequest {

	private  String  userName;

    private String  meetingRoomName;
	
	private Integer meetingRoomPeoples;
	
	private List<RoomEquiment> roomEquiments;

	public String getMeetingRoomName() {
		return meetingRoomName;
	}

	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
	}

	public Integer getMeetingRoomPeoples() {
		return meetingRoomPeoples;
	}

	public void setMeetingRoomPeoples(Integer meetingRoomPeoples) {
		this.meetingRoomPeoples = meetingRoomPeoples;
	}

	public List<RoomEquiment> getRoomEquiments() {
		return roomEquiments;
	}

	public void setRoomEquiments(List<RoomEquiment> roomEquiments) {
		this.roomEquiments = roomEquiments;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
