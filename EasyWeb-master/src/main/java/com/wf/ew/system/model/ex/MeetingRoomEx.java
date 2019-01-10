package com.wf.ew.system.model.ex;

import com.wf.ew.system.model.MeetingRoom;

import java.util.List;

public class MeetingRoomEx extends MeetingRoom {

    private  String equimentIds;

    private String meetRoomId;

    private String counts;

    private  String equipmentNames;

    private List<EquipmentEx> equipmentExes;


    public String getEquimentIds() {
        return equimentIds;
    }

    public void setEquimentIds(String equimentIds) {
        this.equimentIds = equimentIds;
    }

    public String getMeetRoomId() {
        return meetRoomId;
    }

    public void setMeetRoomId(String meetRoomId) {
        this.meetRoomId = meetRoomId;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getEquipmentNames() {
        return equipmentNames;
    }

    public void setEquipmentNames(String equipmentNames) {
        this.equipmentNames = equipmentNames;
    }

    public List<EquipmentEx> getEquipmentExes() {
        return equipmentExes;
    }

    public void setEquipmentExes(List<EquipmentEx> equipmentExes) {
        this.equipmentExes = equipmentExes;
    }
}
