package org.example.admin.controller;


import org.example.admin.pojo.GoodsUnit;
import org.example.admin.service.IGoodsUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 商品单位表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-11-15
 */
@Controller
@RequestMapping("/goodsUnit")
public class GoodsUnitController {

    @Autowired
    private IGoodsUnitService goodsUnitService;


    @RequestMapping("allGoodsUnits")
    @ResponseBody
    public List<GoodsUnit> allGoodsUnits(){
        return goodsUnitService.list();
    }

}
