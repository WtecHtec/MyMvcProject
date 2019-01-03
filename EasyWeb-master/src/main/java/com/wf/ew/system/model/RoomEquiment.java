package com.wf.ew.system.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("room_equiment")
public class RoomEquiment {
	@TableId
    private Integer id;
	
	private Integer equimentId;
	
	private Integer meetroomId;
	
	private Integer counts;

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

	public Integer getEquimentId() {
		return equimentId;
	}

	public void setEquimentId(Integer equimentId) {
		this.equimentId = equimentId;
	}

	public Integer getMeetroomId() {
		return meetroomId;
	}

	public void setMeetroomId(Integer meetroomId) {
		this.meetroomId = meetroomId;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
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
