package com.shiro.controller;

import com.shiro.annotation.Log;
import com.shiro.enums.OperationType;
import com.shiro.enums.OperationUnit;
import com.shiro.pojo.SystemUser;
import com.shiro.utils.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    @Log(detail = "查询信息",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
    @RequiresPermissions("user:show")
    @RequestMapping("/show")
    public String showUser() {
        Session session = SecurityUtils.getSubject().getSession();
        return "张三信息";
    }
}

