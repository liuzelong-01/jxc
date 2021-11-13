package org.example.admin.controller;


import org.example.admin.model.RespBean;
import org.example.admin.pojo.User;
import org.example.admin.query.UserQuery;
import org.example.admin.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

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


    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('1010')")
    public String index(){
        return "user/user";
    }


    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101003')")
    public Map<String,Object> userList(UserQuery userQuery){
        return userService.userList(userQuery);
    }

    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdatePage(Integer id,Model model){
        if (null!=id){
            model.addAttribute("user",userService.getById(id));
        }
        return "user/add_update";
    }

    @RequestMapping("/save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101004')")
    public RespBean saveUser(User user){
        userService.saveUser(user);
        return RespBean.success("用户记录添加成功");
    }

    @RequestMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101005')")
    public RespBean updateUser(User user){
        userService.updateUser(user);
        return RespBean.success("用户记录更新成功");
    }


    @RequestMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101006')")
    public RespBean deleteUser(Integer[] ids ){
        userService.deleteUser(ids);
        return RespBean.success("用户记录删除成功！");
    }


}
