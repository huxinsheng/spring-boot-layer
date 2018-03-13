package com.learn.sbl.action.core;

import com.google.code.kaptcha.Constants;
import com.learn.sbl.enums.RememberMeEnum;
import com.learn.sbl.exception.SblException;
import com.learn.sbl.exception.SblExceptionEnum;
import com.learn.sbl.result.ResultBody;
import com.learn.sbl.web.utils.WebUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
                              @RequestParam(value = "vercode") String vercode,
                              @RequestParam(value = "remember", required = false) String remember) {
        //判断用户名和密码是否正确
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            return ResultBody.error("用户名或者密码不能为空!");
        }
        if (StringUtils.isEmpty(vercode) ) {
            return ResultBody.error("验证码不能为空!");
        }
        String text = (String)WebUtil.getShiroSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!vercode.equalsIgnoreCase(text)){
            return ResultBody.error("验证码输入错误!");
        }
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
            WebUtil.clearKaptcha();
            throw new SblException(SblExceptionEnum.USER_NOT_EXISTED);
        } catch (IncorrectCredentialsException ice) {
            WebUtil.clearKaptcha();
            throw new SblException(SblExceptionEnum.USER_PWD_ERROR);
        } catch (LockedAccountException lae) {
            WebUtil.clearKaptcha();
            return ResultBody.error("验证未通过,账户已锁定!");
        } catch (ExcessiveAttemptsException eae) {
            WebUtil.clearKaptcha();
            return ResultBody.error("对用户[" + account + "]进行登录验证..验证未通过,错误次数过多");
        }
        if (currentSubject.isAuthenticated()) {
            WebUtil.clearKaptcha();
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
