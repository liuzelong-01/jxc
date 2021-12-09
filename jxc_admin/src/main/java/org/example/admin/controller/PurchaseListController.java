package org.example.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.admin.model.RespBean;
import org.example.admin.pojo.Goods;
import org.example.admin.pojo.GoodsUnit;
import org.example.admin.pojo.PurchaseList;
import org.example.admin.pojo.PurchaseListGoods;
import org.example.admin.query.GoodsQuery;
import org.example.admin.query.PurchaseQuery;
import org.example.admin.service.*;
import org.example.admin.utils.AssertUtils;
import org.example.admin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 进货单 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-12-05
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseListController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IPurchaseListService purchaseListService;

    @Autowired
    private IPurchaseListGoodsService purchaseListGoodsService;

    @Autowired
    private IGoodsUnitService goodsUnitService;

    @RequestMapping("index")
    public String index(){
        return "purchase/purchase";
    }


    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(Integer gid, Model model){
        model.addAttribute("goods",goodsService.getById(gid));
        return "purchase/purchase_update";
    }

    @RequestMapping("searchPage")
    public String searchPage(){
        return "purchase/searchPage";
    }

    @RequestMapping("purchaseList")
    @ResponseBody
    public Map<String,Object> purchaseList(PurchaseQuery purchaseQuery){
        System.out.println(purchaseListService.purchaseList(purchaseQuery));
        return purchaseListService.purchaseList(purchaseQuery);
    }


    @RequestMapping("updateStock")
    @ResponseBody
    public RespBean updateStock(Integer goodsId,Integer num, PurchaseList purchaseList, PurchaseListGoods purchaseListGoods){
        if (num<=0||"".equals(num)||null==num){
            return RespBean.error("请输入正确的数量！");
        }
        if (null==purchaseList.getPurchaseNumber()|| StringUtils.isEmpty(purchaseList.getPurchaseNumber())){
            return RespBean.error("请输入采购号！");
        }
        if (purchaseList.getSupplierId().equals(0)||"".equals(purchaseList.getSupplierId())){
            return RespBean.error("请选择供应商！");
        }
        if (null==purchaseList.getAmountPaid()||"".equals(purchaseList.getAmountPaid())){
            return RespBean.error("请点击应付款！");
        }

        //第一张表的添加

        purchaseList.setPurchaseDate(LocalDateTime.now());
        purchaseList.setRemarks("添加进货记录");
        AssertUtils.isTrue(!(purchaseListService.save(purchaseList)),"进货记录添加失败！");
        //第二张表的添加
        purchaseListGoods.setTotal(purchaseListGoods.getPrice()*purchaseListGoods.getNum());
        GoodsUnit unit = goodsUnitService.getOne(new QueryWrapper<GoodsUnit>().eq("id", purchaseListGoods.getUnit()));
        purchaseListGoods.setUnit(unit.getName());
        purchaseListGoods.setPurchaseListId(purchaseList.getId());
        AssertUtils.isTrue(!(purchaseListGoodsService.save(purchaseListGoods)),"进货详情记录添加失败！");
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("id", goodsId));
        goods.setInventoryQuantity(goods.getInventoryQuantity()+ purchaseListGoods.getNum());
        AssertUtils.isTrue(!(goodsService.updateById(goods)),"进货失败！");
        return RespBean.success("进货记录添加成功！");

    }

}
