package com.learn.sbl.pojo.core;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Huxinsheng
 */
@Data
@Alias("menu")
@TableName(value = "sys_menu")
public class MenuPojo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 姓名
     */
    @NotBlank(message = "菜单名称不能为空")
    private String name;
    /**
     * 父菜单id
     */
    @TableField(value = "parent_id")
    private Integer parentId;
    /**
     * 菜单级别
     */
    private Integer levels;
    /**
     * 菜单地址
     */
    private String url;
    /**
     * 菜单图标
     */
    private Integer icon;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private Integer perms;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建者id
     */
    @NotNull(message = "创建者不能为空")
    @TableField("create_user_id")
    private Integer createUserId;
}
