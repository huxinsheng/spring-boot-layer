package com.learn.sbl.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Huxinsheng
 */
@Data
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String account;
    private String password;
    private String name;
    private Integer sex;
    private String birthday;
    private String phone;
    private String roleName;
    private String deptName;
    private Integer status;
    private Long createTime;
    private String createUserName;
}
