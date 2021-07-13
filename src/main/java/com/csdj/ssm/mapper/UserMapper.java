package com.csdj.ssm.mapper;

import com.csdj.ssm.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据登录获取用户名信息
     * @param name
     * @return
     */
    public SysUser getUserByUname(@Param(("uname")) String name);

    /**
     * 保存用户对象
     * @param user 用户对象
     * @return 1 成功 0 失败
     */
    public int addUser(SysUser user) ;

    public List<SysUser> getAllUsers();
}
