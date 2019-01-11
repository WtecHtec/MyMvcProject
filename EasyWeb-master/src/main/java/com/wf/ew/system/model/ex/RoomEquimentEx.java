package com.wf.ew.system.model.ex;

public class RoomEquimentEx {

    private  String  roomName;

    private  String roomPeoples;

    private String equimentInfo;
    
    private Integer meetId;
    
    

    public Integer getMeetId() {
		return meetId;
	}

	public void setMeetId(Integer meetId) {
		this.meetId = meetId;
	}

	public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomPeoples() {
        return roomPeoples;
    }

    public void setRoomPeoples(String roomPeoples) {
        this.roomPeoples = roomPeoples;
    }

    public String getEquimentInfo() {
        return equimentInfo;
    }

    public void setEquimentInfo(String equimentInfo) {
        this.equimentInfo = equimentInfo;
    }
}
