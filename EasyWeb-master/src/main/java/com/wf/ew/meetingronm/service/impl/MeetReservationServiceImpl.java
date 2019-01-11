package com.wf.ew.meetingronm.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wf.ew.common.JsonResult;
import com.wf.ew.meetingronm.service.MeetReservationService;
import com.wf.ew.system.controller.UserController;
import com.wf.ew.system.dao.MeetReservationMapper;
import com.wf.ew.system.model.MeetReservation;
import com.wf.ew.system.model.ex.MeetReservationEx;

import springfox.documentation.spring.web.json.Json;

@Service
public class MeetReservationServiceImpl implements MeetReservationService{
	
	private static final Logger log = LoggerFactory.getLogger(MeetReservationServiceImpl.class);
	
	@Autowired
	private MeetReservationMapper meetReservationMapper;
	
	@Override
	public JsonResult insertMeetReservation(MeetReservation pram) {
		log.info("添加预约会议室信息" + JSONObject.toJSONString(pram));
		
		pram.setCreateTime(new Date());
		pram.setStatus(0);

		List<MeetReservationEx> checks = new ArrayList<>();
		MeetReservationEx ex = new MeetReservationEx();
		ex.setReservationDateStr(new SimpleDateFormat("yyyy-MM-dd").format(pram.getReservationDate()));
		ex.setReservationPeriod(pram.getReservationPeriod());
		ex.setMeetId(pram.getMeetId());
		ex.setStatus(1);
		checks = meetReservationMapper.selectMeetReservations(ex);
		
		if (checks.size() > 0) {
			return JsonResult.error("预约失败,会议室已被预约");
		}
		
		int result =  meetReservationMapper.insert(pram);
		if (result > 0) {
			return JsonResult.ok("预约成功，等待审核");
		}
		return JsonResult.error("预约失败");
	}

	@Override
	public JsonResult selectMeetReservations(MeetReservationEx pram) {
		log.info("查询所有预约信息" + JSONObject.toJSONString(pram));
		List<MeetReservationEx> datas = new ArrayList<>();
		pram.setStatus(1);
		datas =  meetReservationMapper.selectMeetReservations(pram);
		
		Map<String, String> map = new HashMap<>();
        pram.setMineIs(0);
        List<MeetReservationEx> dataMaps = new ArrayList<>();
        pram.setReservationDateStrStart(pram.getReservationDateStr());
        String endDate = getSpecifiedDayBefore(pram.getReservationDateStr(), 30);  
        pram.setReservationDateStrEnd(endDate);
        dataMaps =  meetReservationMapper.selectMeetReservations(pram);
        
		if (datas.size() > 0) {
			for (int i = 0; i < dataMaps.size(); i++) {
 				map.put(dataMaps.get(i).getReservationDateStr(), "");
 			}
			return JsonResult.ok("获取信息成功").put("data", datas).put("datamap", map);
		} 
		return JsonResult.ok("获取信息失败");
	}

	@Override
	public JsonResult selectMeetReservationsByMonth(MeetReservationEx pram) {
         Map<String, String> map = new HashMap<>();
         pram.setMineIs(0);
         List<MeetReservationEx> datas = new ArrayList<>();
 		 pram.setStatus(1);
 		 datas =  meetReservationMapper.selectMeetReservations(pram);
 		if (datas.size() > 0) {
 			for (int i = 0; i < datas.size(); i++) {
 				map.put(datas.get(i).getReservationDateStr(), "");
 			}
			return JsonResult.ok("获取信息成功").put("data", map);
		} 
		return JsonResult.error("获取信息失败");
	}
	
	// 后 n 天 的日期
	public  String getSpecifiedDayBefore(String specifiedDay, int  n){ 
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
		date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day + n); 

		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		return dayBefore; 
		} 

}
