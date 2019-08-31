package com.shiro.service;


import com.shiro.pojo.SystemUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author uncle
 * @since 2019-08-30
 */
public interface ISystemUserService {

    SystemUser selectSystemUserByAccount(String account);

}
