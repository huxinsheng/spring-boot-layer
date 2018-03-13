package com.learn.sbl.service.core;

import com.learn.sbl.mapper.core.UserMapper;
import com.learn.sbl.web.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HuXinsheng
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void addLoginInfo(UserInfo userInfo) {
        userMapper.insertLoginInfo(userInfo);
    }
}
