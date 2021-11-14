package org.example.admin.controller;


import org.example.admin.dto.TreeDto;
import org.example.admin.model.RespBean;
import org.example.admin.pojo.Menu;
import org.example.admin.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;


    @RequestMapping("queryAllMenus")
    @ResponseBody
    public List<TreeDto> queryAllMenus(Integer roleId){
        return menuService.queryAllMenus(roleId);
    }

    @RequestMapping("/index")
    public String index(){
        return "menu/menu";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> menuList(){
        return menuService.menuList();
    }


    @RequestMapping("addMenuPage")
    public String addMenuPage(Integer grade, Integer pId, Model model){
        model.addAttribute("grade",grade);
        model.addAttribute("pId",pId);
        return "menu/add";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean saveMenu(Menu menu){
        menuService.saveMenu(menu);
        return RespBean.success("菜单记录添加成功");
    }

    @RequestMapping("updateMenuPage")
    public String updateMenuPage(Integer id,Model model){
        model.addAttribute("menu",menuService.getById(id));
        return "menu/update";
    }

    @RequestMapping("update")
    @ResponseBody
    public RespBean updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return RespBean.success("菜单记录更新成功！");
    }

    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteMenu(Integer id){
        menuService.deleteMenuById(id);
        return RespBean.success("菜单删除成功！");
    }
}
