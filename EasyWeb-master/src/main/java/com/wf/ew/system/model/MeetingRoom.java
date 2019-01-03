package com.wf.ew.system.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("meeting_room")
public class MeetingRoom {
	@TableId
	private Integer id;
	
	private String  meetingRoomName;
	
	private Integer meetingRoomPeoples;
	
	private Date createTime;
	
	private  String createId;
	
	private Date updateTime;
	
	private  String updateId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	
	
}
