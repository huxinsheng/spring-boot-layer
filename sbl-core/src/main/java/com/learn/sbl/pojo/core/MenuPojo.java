package com.learn.sbl.pojo.core;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.learn.sbl.model.core.MenuModel;
import com.learn.sbl.model.core.PermissionModel;
import com.learn.sbl.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author Huxinsheng
 */
@Data
@Alias("menuPojo")
@TableName(value = "t_sys_menu")
@EqualsAndHashCode(callSuper = false)
public class MenuPojo extends BasePojo {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单主键Id
     */
    @TableField(value = "id")
    @NotNull(message = "菜单id不能为空")
    private String id;
    /**
     * 菜单名称
     */
    @TableField(value = "name")
    @NotNull(message = "菜单名称不能为空")
    private String name;
    /**
     * 菜单地址
     */
    @TableField(value = "url")
    private String url;
    /**
     * 菜单级别
     */
    @TableField(value = "level")
    private Integer level;
    /**
     * 菜单顺序
     */
    @TableField(value = "seq")
    private Integer seq;
    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;
    /**
     * 父菜单ID
     */
    @TableField(value = "parent")
    private String parent;

    @Override
    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
