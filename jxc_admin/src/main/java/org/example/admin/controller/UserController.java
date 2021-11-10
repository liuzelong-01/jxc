package org.example.admin.controller;


import org.example.admin.exceptions.ParamsException;
import org.example.admin.model.RespBean;
import org.example.admin.pojo.User;
import org.example.admin.service.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-11-07
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;



    /**
     * 用户信息设置页面
     * @param
     * @return
     */
    @RequestMapping("setting")
    public String setting(Principal principal, Model model){
        User user = userService.findUserByUserName(principal.getName());
        model.addAttribute("user",user);
        return "user/setting";
    }

    @RequestMapping("updateUserInfo")
    @ResponseBody
    public RespBean updateUserInfo(User user){
            userService.updateUserInfo(user);
            return RespBean.success("用户信息更新成功");
    }

    @RequestMapping("password")
    public String password(){
        return "user/password";
    }

    @RequestMapping("updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(Principal principal, String oldPassword, String newPassword, String confirmPassword){

            userService.updateUserPassword(principal.getName(),oldPassword,newPassword,confirmPassword);
            return RespBean.success("用户密码更新成功！");

    }




}
