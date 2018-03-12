package com.learn.sbl.mapper.core;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.learn.sbl.model.UserModel;
import com.learn.sbl.pojo.common.UserPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
}
