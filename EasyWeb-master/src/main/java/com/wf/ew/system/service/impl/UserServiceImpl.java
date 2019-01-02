package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wf.ew.common.JsonResult;
import com.wf.ew.system.controller.UserController;
import com.wf.ew.system.dao.UserMapper;
import com.wf.ew.system.model.User;
import com.wf.ew.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

    @Override
    public JsonResult updateUser(User pram) {
        log.info("个人信息修改");
        Integer  i  =  baseMapper.updateUser(pram);
        if (i > 0) {
            return  JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

}
