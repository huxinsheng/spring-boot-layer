package com.learn.sbl.cache;

import com.learn.sbl.mapper.core.UserMapper;
import com.learn.sbl.model.UserModel;
import com.learn.sbl.pojo.common.MenuTree;
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
public class CacheService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "usercache", key = "'selectUserById:id_'+#id")
    public UserModel selectUserById(Integer id) {
        Map<String,Object> paramsMap = new HashMap<>(5);
        paramsMap.put("id",id);
        return userMapper.selectOneUser(paramsMap);
    }

    @Cacheable(value = "usercache", key = "'selectUserByAccout:account_'+#account")
    public UserModel selectUserByAccout(String account) {
        Map<String,Object> paramsMap = new HashMap<>(5);
        paramsMap.put("account",account);
        UserModel userModel = userMapper.selectOneUser(paramsMap);
        return userModel;
    }

    @Cacheable(value = "usermenucache", key = "'selectUserMenusByUserId:user_id_'+#userId")
    public UserModel selectUserMenuByUserId(Integer userId) {
        Map<String,Object> paramsMap = new HashMap<>(5);
        UserModel userModel = userMapper.selectOneUser(paramsMap);
        return userModel;
    }
}
