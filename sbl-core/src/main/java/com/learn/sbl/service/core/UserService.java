package com.learn.sbl.service.core;

import com.learn.sbl.constans.ConstansKeys;
import com.learn.sbl.mapper.core.MenuMapper;
import com.learn.sbl.mapper.core.UserMapper;
import com.learn.sbl.model.core.MenuModel;
import com.learn.sbl.model.core.MenuTree;
import com.learn.sbl.model.core.UserModel;
import com.learn.sbl.web.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HuXinsheng
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    MenuMapper menuMapper;

    @Cacheable(value = "usercache", key = "'selectUserById:id_'+#id")
    public UserModel selectUserById(Integer id) {
        Map<String, Object> paramsMap = new HashMap<>(5);
        paramsMap.put("id", id);
        return userMapper.selectOneUser(paramsMap);
    }

    @Cacheable(value = "usercache", key = "'selectUserByAccout:login_id_'+#loginId")
    public UserModel selectUserByAccout(String loginId) {
        Map<String, Object> paramsMap = new HashMap<>(5);
        paramsMap.put("loginId", loginId);
        return userMapper.selectOneUser(paramsMap);
    }

    public List<MenuTree> selectUserMenusByUserId(String userId) {
        List<MenuTree> menuTree = menuMapper.findByUserIdAndParent(userId, ConstansKeys.PARENT_ID);
        return menuTree;
    }

    public void addLoginInfo(UserInfo userInfo) {
        userMapper.insertLoginInfo(userInfo);
    }
}
