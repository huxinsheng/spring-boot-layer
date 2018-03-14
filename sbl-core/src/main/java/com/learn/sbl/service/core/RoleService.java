package com.learn.sbl.service.core;

import com.learn.sbl.mapper.core.RoleMapper;
import com.learn.sbl.mapper.core.RolePermissionMapper;
import com.learn.sbl.mapper.core.UserMapper;
import com.learn.sbl.mapper.core.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

}
