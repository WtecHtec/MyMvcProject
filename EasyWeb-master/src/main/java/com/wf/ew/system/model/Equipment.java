package com.wf.ew.system.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("equipment")
public class Equipment {
	   @TableId
	   private Integer id;
	   
	   private  String  equipmentName;

	   private Date createTime;

	   private  Integer createId;

	   private Date updateTime;

	   private  Integer updateId;
}
