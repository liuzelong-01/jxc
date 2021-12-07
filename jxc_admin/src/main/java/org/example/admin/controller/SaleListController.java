package org.example.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.admin.model.RespBean;
import org.example.admin.pojo.*;
import org.example.admin.query.PurchaseQuery;
import org.example.admin.query.SaleQuery;
import org.example.admin.service.IGoodsService;
import org.example.admin.service.IGoodsUnitService;
import org.example.admin.service.ISaleListGoodsService;
import org.example.admin.service.ISaleListService;
import org.example.admin.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 销售单表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
@Controller
@RequestMapping("/sale")
public class SaleListController {
    @Autowired
    IGoodsService goodsService;

    @Autowired
    ISaleListService saleListService;

    @Autowired
    IGoodsUnitService goodsUnitService;

    @Autowired
    ISaleListGoodsService saleListGoodsService;


    @RequestMapping("index")
    public String index(){
        return "sale/sale";
    }

    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(Integer gid, Model model){
        model.addAttribute("goods",goodsService.getById(gid));
        return "sale/sale_update";
    }

    @RequestMapping("searchPage")
    public String searchPage(){
        return "sale/searchPage";
    }

    @RequestMapping("saleList")
    @ResponseBody
    public Map<String,Object> saleList(SaleQuery saleQuery){
        System.out.println(saleListService.saleList(saleQuery));
        return saleListService.saleList(saleQuery);
    }


    @RequestMapping("updateStock")
    @ResponseBody
    public RespBean updateStock(Integer goodsId, Integer num, SaleList saleList, SaleListGoods saleListGoods){
        //第一张表的添加

        saleList.setSaleDate(LocalDateTime.now());
        saleList.setRemarks("添加销售记录");
        AssertUtils.isTrue(!(saleListService.save(saleList)),"销售记录添加失败！");
        //第二张表的添加
        saleListGoods.setTotal(saleListGoods.getPrice()*saleListGoods.getNum());
        GoodsUnit unit = goodsUnitService.getOne(new QueryWrapper<GoodsUnit>().eq("id", saleListGoods.getUnit()));
        saleListGoods.setUnit(unit.getName());
        saleListGoods.setSaleListId(saleList.getId());
        AssertUtils.isTrue(!(saleListGoodsService.save(saleListGoods)),"销售详情记录添加失败！");
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("id", goodsId));
        goods.setInventoryQuantity(goods.getInventoryQuantity()- saleListGoods.getNum());
        AssertUtils.isTrue(!(goodsService.updateById(goods)),"销售失败！");
        return RespBean.success("销售记录添加成功！");

    }


}
