package com.shiro.mapper;

import com.shiro.pojo.SystemUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author uncle
 * @since 2019-08-30
 */
@Mapper
public interface SystemUserMapper {

    SystemUser selectSystemUserByAccount(String account);

}
