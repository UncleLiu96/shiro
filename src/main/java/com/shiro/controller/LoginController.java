package com.shiro.controller;


import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;

@Controller
@RequestMapping
public class LoginController {

    private Logger logger = Logger.getLogger(this.getClass().getName());


    /**
     * 界面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String defaultLogin() {
        return "login";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login";
    }

    /**
     * 登录提交
     * @param username
     * @param tryCode
     * @param password
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("tryCode") String tryCode,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {
        //判断验证码
        if(StringUtils.isBlank(tryCode)){
            logger.info("验证码为空了！");
            redirectAttributes.addFlashAttribute("message", "验证码不能为空！");
            return "redirect:login";
        }
        Session session = SecurityUtils.getSubject().getSession();
        String code = (String) session.getAttribute("rightCode");
        System.out.println(code+"*************"+tryCode);
        if(!tryCode.equalsIgnoreCase(code)){
            logger.info("验证码错误！");
            redirectAttributes.addFlashAttribute("message", "验证码错误！");
            return "redirect:login";
        }
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String attributeValue = null;
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            attributeValue="未知账户！";

        } catch (IncorrectCredentialsException ice) {
            attributeValue="密码不正确！";
        } catch (LockedAccountException lae) {
            attributeValue= "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            attributeValue= "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            attributeValue= "用户名或密码不正确！";
        }finally {
            redirectAttributes.addFlashAttribute("message", attributeValue);
            if (subject.isAuthenticated()) {
                return "success";
            } else {
                token.clear();
                return "redirect:login";
            }
        }


    }

}
