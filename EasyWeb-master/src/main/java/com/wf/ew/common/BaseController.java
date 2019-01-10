package com.wf.ew.common;

import com.wf.ew.system.dao.RoleAuthoritiesMapper;
import com.wf.ew.system.model.Authorities;
import com.wf.ew.system.model.RoleAuthorities;
import com.wf.ew.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller基类
 * Created by wangfan on 2018-02-22 上午 11:29.
 */
public class BaseController {

    // 获取权限
     @Autowired
     private  RoleAuthoritiesMapper roleAuthoritiesMapper;
    /**
     * 获取当前登录的user
     */
    public  User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object object = authentication.getPrincipal();
            if (object != null) {
                User user = (User) object;
                if (user.getUserId() != null) {
                    List<Authorities> roleAuthorities =  new ArrayList<>();
                    roleAuthorities = roleAuthoritiesMapper.getUserAuthorities(user.getUserId());
                    user.setAuthorities(roleAuthorities);
                }
                return user ;
            }
        }
        return null;
    }

    /**
     * 获取当前登录的userId
     */
    public  Integer getLoginUserId() {
        User loginUser = getLoginUser();
        return loginUser == null ? null : loginUser.getUserId();
    }

    /**
     *  获取当前登录的username
     */
    public  String getLoginUserName() {
        User loginUser = getLoginUser();
        return loginUser == null ? null : loginUser.getUsername();
    }
}
