package com.learn.sbl.action.core;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.learn.sbl.model.core.MenuModel;
import com.learn.sbl.result.Result;
import com.learn.sbl.result.ResultBody;
import com.learn.sbl.web.UserInfo;
import com.learn.sbl.web.annotation.Login;
import com.learn.sbl.web.utils.WebUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @author HuXinsheng
 */
@Controller
public class IndexController {

    @Autowired
    private Producer producer;

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @ApiOperation(value = "获取图形验证码", notes = "用户登录时要使用的图形验证码")
    @GetMapping(value = "captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        WebUtil.getShiroSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
    }


    @ApiOperation(value = "获取用户菜单", notes = "登录成功后获取当前用户菜单信息")
    @ResponseBody
    @GetMapping("getMenus")
    public Result getMenus(@Login UserInfo userInfo) {
        List<MenuModel> menus = userInfo.getMenus();
        Result result = new Result();
        result.setDataList(menus);
        return result;
    }
}
