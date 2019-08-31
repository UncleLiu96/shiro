package com.shiro.service;


import com.shiro.pojo.SystemLog;
import com.shiro.pojo.SystemUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author uncle
 * @since 2019-08-30
 */
public interface ISystemLogService {

    int addLog(SystemLog log);

}
