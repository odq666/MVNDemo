package com.csdj.ssm.service;

import com.csdj.ssm.entity.SysUser;

import java.util.List;

public interface UserService {
    /**
     * 获取登录的用户信息
     * @param uname 用户名
     * @param upwd 密码
     * @return 用户信息
     */
    public SysUser findUserByUnameAndPwd(String uname,String upwd);

    /**
     * 保存用户对象
     * @param user 用户
     * @return 1 注册成功 0 失败
     */
    public int addUser(SysUser user);

    /**
     * 根据名字查询用户信息
     * @param uname 用户名
     * @return 用户信息
     */
    public SysUser findUserByUname(String uname);

    /**
     * 所有的用户集合
     * @return 用户信息
     */
    public List<SysUser> findAllUsers();
}
