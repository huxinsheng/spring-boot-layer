package com.learn.sbl.model.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author kevin
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuTree implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单地址
     */
    private String url;
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
    private List<MenuTree> children;
}
