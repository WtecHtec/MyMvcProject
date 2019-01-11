package com.wf.ew.meetingronm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wf.ew.common.BaseController;
import com.wf.ew.common.JsonResult;
import com.wf.ew.meetingronm.service.MeetReservationService;
import com.wf.ew.meetingronm.service.impl.MeetReservationServiceImpl;
import com.wf.ew.system.model.MeetReservation;
import com.wf.ew.system.model.ex.MeetReservationEx;
import com.wf.ew.system.request.MeetReservationRequest;

@RestController
@RequestMapping("${api.version}/meetreservation")
public class MeetReservationController extends BaseController{
	private static final Logger log = LoggerFactory.getLogger(MeetReservationController.class);
	@Autowired
	private MeetReservationService meetReservationService;
	
	@RequestMapping(value = "/insertMeetReservation", method = RequestMethod.POST)
	public JsonResult insertMeetReservation (MeetReservationRequest pram) {
		try {
			pram.setReservationDate(new SimpleDateFormat("yyyy-MM-dd").parse(pram.getReservationDateStr()));
		} catch (ParseException e) {
			log.info("日期转换错误");
		}
		pram.setCreateId(getLoginUserName());
		return meetReservationService.insertMeetReservation(pram);
	} 
	
	@RequestMapping(value = "/selectMeetReservations", method = RequestMethod.POST)
	public JsonResult selectMeetReservations (MeetReservationEx pram) {
		return meetReservationService.selectMeetReservations(pram);
	}
}
