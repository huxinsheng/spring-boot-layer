package com.learn.sbl.action.core;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.learn.sbl.cache.CacheService;
import com.learn.sbl.result.ResultBody;
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

/**
 * @author HuXinsheng
 */
@Controller
public class IndexController {

    @Autowired
    private Producer producer;

    @Autowired
    private CacheService cacheService;

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    /**
     * 登录页面
     *
     * @return
     */
    @ApiOperation(value = "进入首面", notes = "登录成功进入首先首面")
    @GetMapping(value = "/")
    public String login() {
        return "/index";
    }


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

    @GetMapping(value = "main")
    public String main() {
        return "/main";
    }

    @ResponseBody
    @GetMapping("getMenus")
    public String getMenus() {
        //cacheService.selectCurrentUserMenus();
        return ResultBody.success();
    }
}
