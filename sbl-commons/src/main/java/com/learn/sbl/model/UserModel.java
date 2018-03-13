package com.learn.sbl.model;

import com.learn.sbl.pojo.common.MenuTree;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
    List<MenuTree> menuTrees;
}
