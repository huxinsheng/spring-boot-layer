package com.learn.sbl.security;

import com.learn.sbl.model.core.MenuModel;
import com.learn.sbl.model.core.MenuTree;
import com.learn.sbl.model.core.UserModel;
import com.learn.sbl.service.core.RoleService;
import com.learn.sbl.service.core.UserService;
import com.learn.sbl.web.UserInfo;
import com.learn.sbl.web.contants.WebConstants;
import com.learn.sbl.web.utils.WebUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


/**
 * @author HuXinsheng
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private RoleService roleService;

    /**
     * 授权(验证权限时调用)
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     * <p>
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.err.println("===========进入doGetAuthorizationInfo==========");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //从principalCollection获取主身份信息
        //将getPrimaryPrincipal()返回的值强制转换为真实身份信息【在doGetAuthenticationInfo()认证通过填充到SimpleAuthenticationInfo中的身份信息】
        String account = (String) principalCollection.getPrimaryPrincipal();
        UserModel userModel = userService.selectUserByAccout(account);
        // 用户拥有的角色
        //authorizationInfo.addRole(userModel.getRoleName());
        // 用户拥有的权限
        authorizationInfo.addStringPermission("user:list");
        authorizationInfo.addStringPermission("user:add");
        return authorizationInfo;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户的输入的账号.
        String account = (String) authenticationToken.getPrincipal();
        // 查询用户信息
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserModel userModel = userService.selectUserByAccout(account);
        // 账号存在
        if (!StringUtils.isEmpty(userModel)) {
            UserInfo userInfo = userModel.getLoginInfo();
            if (null == userInfo) {
                userInfo = new UserInfo();
                userInfo.setId(userModel.getId());
                userInfo.setName(userModel.getName());
                userInfo.setLoginId(userModel.getLoginId());
                userInfo.setLoginTime(new Date());
                userInfo.setIp(WebUtil.getIpAddr());
            }

            List<MenuTree> trees = userService.selectUserMenusByUserId(userModel.getId());
            userInfo.setMenus(trees);
            WebUtil.getShiroSession().setAttribute(WebConstants._USER, userInfo);
            userService.addLoginInfo(userInfo);
            return new SimpleAuthenticationInfo(account, userModel.getPassword(), userModel.getName());
        }
        return null;
    }


}
