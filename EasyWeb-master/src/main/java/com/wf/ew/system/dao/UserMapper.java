package com.wf.ew.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wf.ew.system.model.User;
import com.wf.ew.system.model.UserRole;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    User getByUsername(String username);
    Integer updateUser(User parm);
     List<UserRole> getRolesByUserId(Integer userId);
}
