package org.example.admin.controller;


import org.example.admin.dto.TreeDto;
import org.example.admin.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

}
