package com.learn.sbl.pojo.common;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@TableName(value = "sys_user")
@Data
public class UserPojo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 登录账号(邮箱格式)
     */
    @Email(message = "账号(邮箱)格式不正确")
    @NotBlank(message = "登录账号不能为空")
    private String account;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度为6-20")
    private String password;
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;
    /**
     * 性别(1：男，2：女)
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 电话
     */
    private String phone;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 部门id
     */
    @TableField("dept_id")
    private Integer deptId;
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
