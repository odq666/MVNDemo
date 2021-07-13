package com.csdj.ssm.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.csdj.ssm.entity.SysUser;
import com.csdj.ssm.mapper.UserMapper;
import com.csdj.ssm.service.UserService;
import com.csdj.ssm.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private ShardedJedisPool shardedJedisPool;

    @Override
    public SysUser findUserByUnameAndPwd(String uname, String upwd) {
        SysUser sysUser = userMapper.getUserByUname(uname);
        upwd = MD5Util.md5(upwd);
        if (sysUser != null){
            if (!upwd.equals(sysUser.getUpwd())){
                logger.info("登录失败，密码不正确");
                return null;
            }
        }else {
            logger.info("登录失败，用户名不正确");
            return null;
        }
        return sysUser;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public int addUser(SysUser user) {
        user.setUpwd(MD5Util.md5(user.getUpwd()));
        return userMapper.addUser(user);
    }

    @Override
    public SysUser findUserByUname(String uname) {
        return userMapper.getUserByUname(uname);
    }

    @Override
    public List<SysUser> findAllUsers() {
        String key = "allUsers";
        // redis缓存
        ShardedJedis jedis = shardedJedisPool.getResource();

        //   看下内存中是否有数据  有直接内存中拿
        if (jedis.exists(key)){
            System.out.println("从缓存中拿的数据");
            String jsonUsers = jedis.get(key);
            //将字符串转成集合
            List<SysUser> sysUsers = JSONArray.parseArray(jsonUsers, SysUser.class);
            return sysUsers;
        }else {
            System.out.println("从数据库中拿的数据");
            //  没有就去数据库拿
            List<SysUser> allUsers = userMapper.getAllUsers();
            String allJson = JSONArray.toJSONString(allUsers);
            jedis.set(key,allJson);
            jedis.expire(key,100);
            return allUsers;
        }
    }
}
