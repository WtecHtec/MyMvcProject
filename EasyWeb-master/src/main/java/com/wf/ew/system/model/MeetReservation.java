package com.wf.ew.system.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("meet_reservation")
public class MeetReservation {

	@TableId
	private Integer id;
	
	private Integer meetId;
	
	private Integer reservationPeople;
	
	private Date reservationDate;
	
	private Integer reservationPeriod;
	
	private String  content;
	
	private Integer status;
	
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMeetId() {
		return meetId;
	}

	public void setMeetId(Integer meetId) {
		this.meetId = meetId;
	}

	public Integer getReservationPeople() {
		return reservationPeople;
	}

	public void setReservationPeople(Integer reservationPeople) {
		this.reservationPeople = reservationPeople;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Integer getReservationPeriod() {
		return reservationPeriod;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setReservationPeriod(Integer reservationPeriod) {
		this.reservationPeriod = reservationPeriod;
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
