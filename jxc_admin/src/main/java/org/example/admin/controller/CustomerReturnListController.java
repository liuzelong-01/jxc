package org.example.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.admin.model.RespBean;
import org.example.admin.pojo.*;
import org.example.admin.query.ReturnQuery;
import org.example.admin.query.SaleQuery;
import org.example.admin.service.ICustomerReturnListGoodsService;
import org.example.admin.service.ICustomerReturnListService;
import org.example.admin.service.IGoodsService;
import org.example.admin.service.IGoodsUnitService;
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
 * 客户退货单表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
@Controller
@RequestMapping("/customerReturn")
public class CustomerReturnListController {
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ICustomerReturnListService customerReturnListService;

    @Autowired
    private IGoodsUnitService goodsUnitService;

    @Autowired
    private ICustomerReturnListGoodsService customerReturnListGoodsService;



    @RequestMapping("index")
    public String index(){
        return "customerReturn/return";
    }

    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(Integer gid, Model model){
        model.addAttribute("goods",goodsService.getById(gid));
        return "customerReturn/return_update";
    }

    @RequestMapping("returnList")
    @ResponseBody
    public Map<String,Object> saleList(ReturnQuery returnQuery){
        System.out.println(customerReturnListService.returnList(returnQuery));
        return customerReturnListService.returnList(returnQuery);
    }


    @RequestMapping("searchPage")
    public String searchPage(){
        return "customerReturn/searchPage";
    }



    @RequestMapping("updateStock")
    @ResponseBody
    public RespBean updateStock(Integer goodsId, CustomerReturnList customerReturnList, CustomerReturnListGoods customerReturnListGoods){
        //第一张表的添加

        customerReturnList.setCustomerReturnDate(LocalDateTime.now());
        customerReturnList.setRemarks("添加顾客退货记录");
        AssertUtils.isTrue(!(customerReturnListService.save(customerReturnList)),"顾客退货记录添加失败！");
        //第二张表的添加
        customerReturnListGoods.setTotal(customerReturnListGoods.getPrice()*customerReturnListGoods.getNum());
        GoodsUnit unit = goodsUnitService.getOne(new QueryWrapper<GoodsUnit>().eq("id", customerReturnListGoods.getUnit()));
        customerReturnListGoods.setUnit(unit.getName());
        customerReturnListGoods.setCustomerReturnListId(customerReturnList.getId());
        AssertUtils.isTrue(!(customerReturnListGoodsService.save(customerReturnListGoods)),"顾客退货详情记录添加失败！");
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("id", goodsId));
        if (customerReturnListGoods.getNum()<0){
            return RespBean.error("退货记录添加失败！退货数量不能小于0！");
        }
        goods.setInventoryQuantity(goods.getInventoryQuantity() + customerReturnListGoods.getNum());
        AssertUtils.isTrue(!(goodsService.updateById(goods)),"退货失败！");
        return RespBean.success("退货记录添加成功！");

    }




}
