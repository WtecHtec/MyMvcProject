package com.wf.ew.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wf.ew.system.model.RoomEquiment;

import java.util.List;

public interface RoomEquimentMapper extends BaseMapper<RoomEquiment> {

     Integer insertRoomEquimentList(List<RoomEquiment> pram);

}
