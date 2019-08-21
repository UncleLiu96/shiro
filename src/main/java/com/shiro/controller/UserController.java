package com.shiro.controller;

import com.shiro.utils.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    @RequiresPermissions("user:show")
    @RequestMapping("/show")
    public String showUser() {
        return "张三信息";
    }
}

