package com.shiro.service.impl;

import com.shiro.mapper.SystemUserMapper;
import com.shiro.pojo.SystemUser;
import com.shiro.service.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
public class SystemUserServiceImpl implements ISystemUserService {

    @Resource
    private SystemUserMapper userMapper;

    @Override
    public SystemUser selectSystemUserByAccount(String account) {
        return userMapper.selectSystemUserByAccount(account);
    }
}
