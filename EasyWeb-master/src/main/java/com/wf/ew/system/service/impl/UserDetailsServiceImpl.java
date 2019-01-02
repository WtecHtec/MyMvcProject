package com.wf.ew.system.service.impl;

import com.wf.ew.system.dao.AuthoritiesMapper;
import com.wf.ew.system.dao.UserMapper;
import com.wf.ew.system.model.Authorities;
import com.wf.ew.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AuthoritiesMapper authoritiesMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getByUsername(username);
        System.out.println("==================================");
        System.out.println(user.getUsername() + "--" + user.getPassword());
        System.out.println("==================================");
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        List<String> authoritys = authoritiesMapper.listByUserId(user.getUserId());
        user.setAuthorities(getGrantedAuthority(authoritys));
        return user;
    }

    private List<Authorities> getGrantedAuthority(List<String> authoritys) {
        List<Authorities> grantedAuthorities = new ArrayList<>();
        for (String auth : authoritys) {
            Authorities ga = new Authorities();
            ga.setAuthority(auth);
            grantedAuthorities.add(ga);
        }
        return grantedAuthorities;
    }
}
