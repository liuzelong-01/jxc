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
import org.example.admin.utils.StringUtils;
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
        if (num<=0||"".equals(num)||null==num){
            return RespBean.error("请输入正确的数量！");
        }
        if (null==saleList.getSaleNumber()|| StringUtils.isEmpty(saleList.getSaleNumber())){
            return RespBean.error("请输入销售号！");
        }
        if (saleList.getCustomerId().equals(0)||"".equals(saleList.getCustomerId())){
            return RespBean.error("请选择顾客！");
        }
        if (null==saleList.getAmountPaid()||"".equals(saleList.getAmountPaid())){
            return RespBean.error("请点击应付款！");
        }
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("id", goodsId));
        if (goods.getInventoryQuantity()- saleListGoods.getNum()<0){
            return RespBean.error("库存不足！");
        }
        goods.setInventoryQuantity(goods.getInventoryQuantity()- saleListGoods.getNum());
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

        AssertUtils.isTrue(!(goodsService.updateById(goods)),"销售失败！");
        return RespBean.success("销售记录添加成功！");

    }


}
