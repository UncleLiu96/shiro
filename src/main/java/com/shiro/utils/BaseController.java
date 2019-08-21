package com.shiro.utils;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
    /**
     * 捕获没有权限时的异常
     * @return
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public Map<String, Object> authorizationException(){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("没有权限");
        map.put("success", true);
        map.put("msg", "当前用户没有此权限");
        return map;
    }
}
