package com.learn.sbl.pojo.core;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableName;
import com.learn.sbl.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author HuXinsheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_sys_user_role")
public class UserRolePojo extends BasePojo {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;

    @Override
    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
