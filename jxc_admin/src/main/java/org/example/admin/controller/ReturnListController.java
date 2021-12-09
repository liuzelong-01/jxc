package org.example.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.admin.model.RespBean;
import org.example.admin.pojo.*;
import org.example.admin.query.PurchaseQuery;
import org.example.admin.query.ReturnQuery;
import org.example.admin.service.IGoodsService;
import org.example.admin.service.IGoodsUnitService;
import org.example.admin.service.IReturnListGoodsService;
import org.example.admin.service.IReturnListService;
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
 * 退货单表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
@Controller
@RequestMapping("/return")
public class ReturnListController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IReturnListService returnListService;

    @Autowired
    private IGoodsUnitService goodsUnitService;

    @Autowired
    private IReturnListGoodsService returnListGoodsService;

    @RequestMapping("index")
    public String index(){
        return "return/return";
    }

    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(Integer gid, Model model){
        model.addAttribute("goods",goodsService.getById(gid));
        return "return/return_update";
    }

    @RequestMapping("searchPage")
    public String searchPage(){
        return "return/searchPage";
    }

    @RequestMapping("returnList")
    @ResponseBody
    public Map<String,Object> purchaseList(ReturnQuery returnQuery){
        System.out.println(returnListService.returnList(returnQuery));
        return returnListService.returnList(returnQuery);
    }

    @RequestMapping("updateStock")
    @ResponseBody
    public RespBean updateStock(Integer goodsId, Integer num, ReturnList returnList, ReturnListGoods returnListGoods){

        if (num<=0||"".equals(num)||null==num){
            return RespBean.error("请输入正确的数量！");
        }
        if (null==returnList.getReturnNumber()|| StringUtils.isEmpty(returnList.getReturnNumber())){
            return RespBean.error("请输入退货号！");
        }
        if (returnList.getSupplierId().equals(0)||"".equals(returnList.getSupplierId())){
            return RespBean.error("请选择供应商！");
        }
        if (null==returnList.getAmountPaid()||"".equals(returnList.getAmountPaid())){
            return RespBean.error("请点击应付款！");
        }

        //第一张表的添加

        returnList.setReturnDate(LocalDateTime.now());
        returnList.setRemarks("添加退货记录");
        AssertUtils.isTrue(!(returnListService.save(returnList)),"进货记录添加失败！");
        //第二张表的添加
        returnListGoods.setTotal(returnListGoods.getPrice()*returnListGoods.getNum());
        GoodsUnit unit = goodsUnitService.getOne(new QueryWrapper<GoodsUnit>().eq("id", returnListGoods.getUnit()));
        returnListGoods.setUnit(unit.getName());
        returnListGoods.setReturnListId(returnList.getId());
        AssertUtils.isTrue(!(returnListGoodsService.save(returnListGoods)),"退货详情记录添加失败！");
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("id", goodsId));
        if (goods.getInventoryQuantity()- returnListGoods.getNum()<0){
            return RespBean.error("退货记录添加失败！库存不足~");
        }
        goods.setInventoryQuantity(goods.getInventoryQuantity()- returnListGoods.getNum());
        AssertUtils.isTrue(!(goodsService.updateById(goods)),"退货失败！");
        return RespBean.success("退货记录添加成功！");

    }

}
