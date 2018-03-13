package com.learn.sbl.model.core;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

/**
 * @author kevin
 */
@Data
public class MenuModel {
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
     * 是否叶子节点
     */
    private boolean leaf;
    /**
     * 是否展开
     */
    private boolean expanded;
    /**
     * 子菜单列表
     */
    private List<MenuModel> subMenus;
    /**
     * 菜单拥有权限列表
     */
    private List<PermissionModel> permissions;
    /**
     * 是否选中
     */
    private boolean checked;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
