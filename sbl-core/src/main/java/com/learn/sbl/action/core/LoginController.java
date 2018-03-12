package com.learn.sbl.action.core;

import com.learn.sbl.enums.RememberMeEnum;
import com.learn.sbl.exception.SblException;
import com.learn.sbl.exception.SblExceptionEnum;
import com.learn.sbl.result.ResultBody;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author HuXinsheng
 */
@Controller
public class LoginController {


    private static final Logger log = LoggerFactory.getLogger(LoginController.class);



    /**
     * 登录页面
     *
     * @return
     */
    @ApiOperation(value = "进入登录页面", notes = "未登录访问进入登录页面")
    @GetMapping(value = "/login")
    public String login() {
        return "/login";
    }

    /**
     * ajax登录请求
     */

    @ApiOperation(value = "用户登录", notes = "根据用户账号、密码登录系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "登录账号", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "remember", value = "记住我", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/login")
    @ResponseBody
    public String submitLogin(@RequestParam(value = "account") String account,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "remember", required = false) String remember) {
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        if (RememberMeEnum.ON.getRemark().equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        //获取当前的Subject
        Subject currentSubject = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到ShiroRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentSubject.login(token);
        } catch (UnknownAccountException uae) {
            throw new SblException(SblExceptionEnum.USER_NOT_EXISTED);
        } catch (IncorrectCredentialsException ice) {
            throw new SblException(SblExceptionEnum.USER_PWD_ERROR);
        } catch (LockedAccountException lae) {
            log.error("LockedAccountException验证未通过,账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.info("对用户[" + account + "]进行登录验证..验证未通过,错误次数过多");
        }
        if (currentSubject.isAuthenticated()) {
            log.info("用户[" + account + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            return ResultBody.success(token);
        }
        return null;
    }

    /**
     * 退出
     *
     * @return
     */
    @GetMapping(value = "/logout")
    public void logout() {
        //退出
        SecurityUtils.getSubject().logout();
    }
}
