package org.example.admin.controller;


import org.example.admin.model.RespBean;
import org.example.admin.pojo.Role;
import org.example.admin.pojo.User;
import org.example.admin.query.RoleQuery;
import org.example.admin.service.IRoleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;


    @RequestMapping("index")
    public String index(){
        return "role/role";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> roleList(RoleQuery roleQuery){
       return roleService.roleList(roleQuery);
    }

    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdatePage(Integer id, Model model){
        if (null!=id){
            model.addAttribute("role",roleService.getById(id));
        }
        return "role/add_update";
    }


    @RequestMapping("/save")
    @ResponseBody
    public RespBean saveRole(Role role){
        roleService.saveRole(role);
        return RespBean.success("角色记录添加成功");
    }

    @RequestMapping("/update")
    @ResponseBody
    public RespBean updateRole(Role role){
        roleService.updateRole(role);
        return RespBean.success("角色记录更新成功");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean deleteRole(Integer id){
        roleService.deleteRole(id);
        return RespBean.success("角色记录删除成功");
    }

    @RequestMapping("/queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return this.roleService.queryAllRoles(userId);
    }


}
