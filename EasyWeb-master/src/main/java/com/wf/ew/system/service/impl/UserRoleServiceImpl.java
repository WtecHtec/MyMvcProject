package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wf.ew.system.dao.UserMapper;
import com.wf.ew.system.dao.UserRoleMapper;
import com.wf.ew.system.model.User;
import com.wf.ew.system.model.UserRole;
import com.wf.ew.system.service.UserRoleService;
import com.wf.ew.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018-12-19 下午 4:10.
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
