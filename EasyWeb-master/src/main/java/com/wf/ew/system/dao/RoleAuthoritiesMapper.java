package com.wf.ew.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wf.ew.system.model.Authorities;
import com.wf.ew.system.model.RoleAuthorities;

import java.util.List;

public interface RoleAuthoritiesMapper extends BaseMapper<RoleAuthorities> {

    int deleteTrash();

    List<Authorities> getUserAuthorities(Integer userId);
}
