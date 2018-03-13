package com.learn.sbl.service.core;

import com.learn.sbl.mapper.core.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    @Autowired
    private MenuService menuMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
}
