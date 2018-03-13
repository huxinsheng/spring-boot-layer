package com.learn.sbl.mapper.core;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.learn.sbl.model.core.UserModel;
import com.learn.sbl.pojo.core.UserPojo;
import com.learn.sbl.web.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author HuXinsheng
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<UserPojo> {
    /**
     * 分页查询用户列表
     *
     * @param page
     * @return
     */
    List<UserModel> selectUserList(Pagination page);

    /**
     * 根据条件查询用户
     *
     * @param paramMap
     * @return
     */
    UserModel selectOneUser(Map<String, Object> paramMap);

    /**
     * 根据登录id查询登录用户信息
     *
     * @param loginId
     * @return
     */
    String getLoginInfoByLoginId(String loginId);

    /**
     * 新增登录用户信息
     *
     * @param userInfo
     */
    void insertLoginInfo(UserInfo userInfo);
}
