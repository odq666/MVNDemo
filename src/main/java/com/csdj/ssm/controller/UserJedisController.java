package com.csdj.ssm.controller;

import com.csdj.ssm.entity.SysUser;
import com.csdj.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserJedisController {

    @Resource
    private UserService userService;

    @RequestMapping("showUsers.shtml")
    public String showUsers(Model model){
        List<SysUser> allUser = userService.findAllUsers();
        model.addAttribute("users",allUser);
        return "show";
    }
}
