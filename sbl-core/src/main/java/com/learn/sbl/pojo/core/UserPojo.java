package com.learn.sbl.pojo.core;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.learn.sbl.pojo.BasePojo;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Huxinsheng
 */
@TableName(value = "t_sys_user")
@Data
public class UserPojo extends BasePojo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 登录id
     */
    @TableField("loginId")
    @NotNull(message = "登录用户名不能为空!")
    private String loginId;
    /**
     * 用户名称
     */
    @TableField("name")
    @NotNull(message = "用户名不能为空!")
    private String name;
    /**
     * 登录密码
     */
    @TableField("password")
    @NotNull(message = "密码不能为空!")
    private String password;
    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 用户邮箱
     */
    @TableField("disabled")
    private boolean disabled;

    @Override
    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
