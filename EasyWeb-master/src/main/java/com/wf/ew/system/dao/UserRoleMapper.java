package com.wf.ew.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wf.ew.system.model.UserRole;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    Integer delUserRoleByRoleIds(List<UserRole> ids);

    List<String> getUserRoleByUserId(Integer userId);
}
