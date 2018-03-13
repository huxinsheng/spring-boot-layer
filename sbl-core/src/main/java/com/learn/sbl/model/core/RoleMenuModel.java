package com.learn.sbl.model.core;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author kevin
 */
@Data
public class RoleMenuModel {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
