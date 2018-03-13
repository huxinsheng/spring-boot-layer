package com.learn.sbl.model.core;

import com.alibaba.fastjson.JSON;
import com.learn.sbl.model.BaseModel;
import com.learn.sbl.pojo.core.MenuTree;
import com.learn.sbl.web.UserInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Huxinsheng
 */
@Data
public class UserModel extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserModel() {

    }

    public UserModel(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    /**
     * 主键id
     */
    private String id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 登录id
     */
    private String loginId;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 是否禁用
     */
    private boolean disabled;

    /**
     * 用户角色列表
     */
    private List<RoleModel> roles;

    /**
     * 登录信息
     */
    private UserInfo loginInfo;

    @Override
    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
