package com.csdj.ssm.controller;

import com.csdj.ssm.entity.SysUser;
import com.csdj.ssm.service.UserService;

import com.csdj.ssm.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    @Value("${uploadPath}")
    private String path;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "login.shtml",method = RequestMethod.POST)
    public String login(String uname, String upwd, HttpSession session){
        SysUser sysUser = userService.findUserByUnameAndPwd(uname, upwd);

        if(sysUser != null){
            session.setAttribute("sysUser",sysUser);
            return "redirect:index.jsp";
        }else {
            return "redirect:index.jsp";
        }

     }

     @RequestMapping("valUname.shtml")
     @ResponseBody
     public Object valUname(String uname){
         Map<String,Object> resultMap = new HashMap<>();
         SysUser user = userService.findUserByUname(uname);
         if (user == null){
             resultMap.put("resultCode","0000");
         }else {
             resultMap.put("resultCode","1001");
         }
         return resultMap;
     }

     @RequestMapping(value = "addUser.shtml",method = RequestMethod.POST)
     public String addUser(SysUser user,
                           @RequestParam(value = "uimgFile",required = false) MultipartFile uimgFile){

         if (uimgFile != null && uimgFile.getSize() > 0){
             String fileName = uimgFile.getOriginalFilename();
             fileName = System.currentTimeMillis() + fileName;

             String type = uimgFile.getContentType();
             if (type.startsWith("image")){
                 String pathName = path + File.separatorChar + fileName;

                 File outFile = new File(pathName);
                 try {
                     uimgFile.transferTo(outFile);
                     logger.info("上传成功");
                     user.setUimg(fileName);
                 } catch (IOException e) {
                     e.printStackTrace();
                     logger.info("上传失败");
                     user.setUimg("aa.jpg" );
                 } finally {
                 }
             }else {
                 logger.info("图像类型错误，使用默认头像");
                 user.setUimg("aa.jpg" );
             }

         }
         //调用保存
         int i = userService.addUser(user);
         if (i == 1){
             logger.info("添加成功，用户编号为:" + user.getUid());
             return "redirect:login.jsp";
         }else {
             return "redirect:reg.jsp";
         }

     }
}
