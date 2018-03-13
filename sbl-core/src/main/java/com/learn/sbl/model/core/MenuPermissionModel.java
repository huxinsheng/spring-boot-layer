package com.learn.sbl.model.core;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author kevin
 */
@Data
public class MenuPermissionModel {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 权限id
     */
    private String permissionId;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
