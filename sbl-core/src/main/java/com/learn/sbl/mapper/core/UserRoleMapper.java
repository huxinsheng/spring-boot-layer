package com.learn.sbl.mapper.core;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.learn.sbl.model.core.RoleModel;
import com.learn.sbl.pojo.core.UserRolePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author HuXinsheng
 */
@Mapper
@Component
public interface UserRoleMapper extends BaseMapper<UserRolePojo> {
    /**
     * 根据用户id查询所拥有的角色信息列表
     *
     * @param userId
     * @return
     */
    List<RoleModel> findByUserId(Integer userId);

    /**
     * 批量插入用户角色信息
     *
     * @param userRolePojos
     */
    void insertAll(List<UserRolePojo> userRolePojos);

    /**
     * 根据条件查询角色信息
     *
     * @param paramMap
     * @return
     */
    List<RoleModel> findByCondition(Map<String, Object> paramMap);

    /**
     * 根据用户id或角色id删除用户角色关联关系
     *
     * @param userId
     * @param roleId
     */
    void deleteByUserIdAndRoleId(@Param("userId") String userId, @Param("roleId") String roleId);
}
