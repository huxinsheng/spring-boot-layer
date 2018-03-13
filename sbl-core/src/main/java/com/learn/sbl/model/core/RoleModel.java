package com.learn.sbl.model.core;

import com.alibaba.fastjson.JSON;
import com.learn.sbl.model.MenuModel;
import lombok.Data;

import java.util.List;

/**
 * @author kevin
 */
@Data
public class RoleModel {
    /**
     * 主键id
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String desc;
    /**
     * 角色标识
     */
    private String sign;
    /**
     * 角色拥有菜单列表
     */
    private List<MenuModel> menus;
    /**
     * 是否选中
     */
    private boolean checked;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
