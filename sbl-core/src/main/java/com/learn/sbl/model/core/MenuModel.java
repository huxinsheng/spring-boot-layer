package com.learn.sbl.model.core;

import com.alibaba.fastjson.JSON;
import com.learn.sbl.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author kevin
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuModel extends BaseModel {
    /**
     * 菜单主键Id
     */
    private String id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单名称
     */
    private String parentName;
    /**
     * 菜单地址
     */
    private String url;
    /**
     * 视图
     */
    private String view;
    /**
     * 菜单级别
     */
    private Integer level;
    /**
     * 菜单顺序
     */
    private Integer seq;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 父菜单ID
     */
    private String parent;
    /**
     * 子菜单列表
     */
    private List<MenuModel> subMenus;
    /**
     * 菜单拥有权限列表
     */
    private List<PermissionModel> permissions;

    @Override
    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
