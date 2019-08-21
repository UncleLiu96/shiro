package com.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class CustomRealm extends AuthorizingRealm {

    /**
     * 权限相关
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //账户
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //从数据库获取账户权限信息
        Set<String> stringSet = new HashSet<>();
        stringSet.add("user:show");
        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        return info;
    }

    /**
     * 身份认证
     * 这里可以注入userService,为了方便演示直接写死账户和密码
     * 获取即将需要认证的信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());

        System.out.println(userPwd);
        //根据用户名从数据库获取密码
        String password = "123";
        if (userName == null) {
            throw new AccountException("用户名不正确");
        } else if (!userPwd.equals(password )) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(userName, password,getName());
    }



}
