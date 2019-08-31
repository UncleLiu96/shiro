package com.shiro.service.impl;

import com.shiro.mapper.SystemLogMapper;
import com.shiro.mapper.SystemUserMapper;
import com.shiro.pojo.SystemLog;
import com.shiro.pojo.SystemUser;
import com.shiro.service.ISystemLogService;
import com.shiro.service.ISystemUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author uncle
 * @since 2019-08-30
 */
@Service
@Transactional
public class SystemLogServiceImpl implements ISystemLogService {

    @Resource
    private SystemLogMapper logMapper;

    @Override
    public int addLog(SystemLog log) {
        return logMapper.addLog(log);
    }
}
