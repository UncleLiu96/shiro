package com.shiro.realm;

import com.shiro.utils.Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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
        System.out.println("-------权限相关--------");
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
        //根据用户名从数据库获取密码
        String password = "89267a06ce552c28e3edc11be28e4f80"; //  使用明文密码：123加密后
//        if (userName == null) {
//            throw new AccountException("用户名不正确");
//        } else if (!userPwd.equals(password )) {
//            throw new AccountException("密码不正确");
//        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        ByteSource salt = ByteSource.Util.bytes(userName);
        return new SimpleAuthenticationInfo(userName, password, salt, getName());
    }



}
