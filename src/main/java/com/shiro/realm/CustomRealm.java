package com.shiro.realm;

import com.shiro.pojo.SystemUser;
import com.shiro.service.ISystemUserService;
import com.shiro.utils.Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private ISystemUserService iSystemUserService;

    /**
     * 权限相关
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------权限相关--------");
        //账户
        SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getPrincipal();
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
        String account = (String) authenticationToken.getPrincipal();
        //String userPwd = new String((char[]) authenticationToken.getCredentials());
        //根据账户从数据库获取密码
        SystemUser systemUser = iSystemUserService.selectSystemUserByAccount(account);
        if(systemUser==null){
            throw new AccountException("用户名不正确");
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        ByteSource salt = ByteSource.Util.bytes(account);
        //下面使用systemUser对象
        return new SimpleAuthenticationInfo(systemUser, systemUser.getPsd(), salt, getName());
        //获取登录信息方式为
//        SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        //下面使用account参数
//        return new SimpleAuthenticationInfo(account, systemUser.getPsd(), salt, getName());
        //获取登录信息方式为
//        String account = (String) SecurityUtils.getSubject().getPrincipal();
    }



}
