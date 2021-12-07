package org.example.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.admin.model.RespBean;
import org.example.admin.pojo.*;
import org.example.admin.query.ReturnQuery;
import org.example.admin.service.*;
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
 * 报损单表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
@Controller
@RequestMapping("/damage")
public class DamageListController {
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IDamageListService damageListService;

    @Autowired
    private IGoodsUnitService goodsUnitService;

    @Autowired
    private IDamageListGoodsService damageListGoodsService;

    @Autowired
    private IUserService userService;



    @RequestMapping("index")
    public String index(){
        return "damage/damage";
    }

    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(Integer gid, Model model){
      model.addAttribute("goods",goodsService.getById(gid));
        return "damage/damage_update";
    }

    @RequestMapping("toDamageSearchPage")
    public String searchPage(){
        return "damage/searchPage";
    }

    @RequestMapping("damageSearchPage")
    public String damageSearchPage(){
        return "damage/damageSearchPage";
    }

    @RequestMapping("damageList")
    @ResponseBody
    public Map<String,Object> damageList(ReturnQuery returnQuery){
        System.out.println(damageListService.returnList(returnQuery));
        return damageListService.returnList(returnQuery);
    }

    @RequestMapping("damageAllList")
    @ResponseBody
    public Map<String,Object> damageAllList(ReturnQuery returnQuery){
        System.out.println(damageListService.returnAllList(returnQuery));
        return damageListService.returnAllList(returnQuery);
    }



    @RequestMapping("toUpdateDamageInfoPage")
    public String toUpdateDamageInfoPage(Integer did, Model model){
        DamageList damageList=damageListService.getById(did);
        DamageListGoods damageListGoods=damageListGoodsService.getOne(new QueryWrapper<DamageListGoods>().eq("damage_list_id",did));
        model.addAttribute("damageList",damageList);
        model.addAttribute("damageListGoods",damageListGoods);
        User user=userService.getOne(new QueryWrapper<User>().eq("id",damageList.getUserId()));
        model.addAttribute("userName",user);
        return "damage/damageStock_update";
    }


    @RequestMapping("updateDamage")
    @ResponseBody
    public RespBean updateStock(Integer goodsId, Integer num, DamageList damageList,DamageListGoods damageListGoods){
        //第一张表的添加

        damageList.setDamageDate(LocalDateTime.now());
        damageList.setRemarks("0");
        AssertUtils.isTrue(!(damageListService.save(damageList)),"报损记录添加失败！");
        //第二张表的添加
        damageListGoods.setTotal(damageListGoods.getPrice()*damageListGoods.getNum());
        GoodsUnit unit = goodsUnitService.getOne(new QueryWrapper<GoodsUnit>().eq("id", damageListGoods.getUnit()));
        damageListGoods.setUnit(unit.getName());
        damageListGoods.setDamageListId(damageList.getId());
        AssertUtils.isTrue(!(damageListGoodsService.save(damageListGoods)),"报损详情记录添加失败！");

        return RespBean.success("商品报损成功！");
    }

    @RequestMapping("updateDamageStock")
    @ResponseBody
    public RespBean updateDamageStock(Integer goodsId, Integer num,Integer damageListId){
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("id", goodsId));
        if (num<0){
            return RespBean.error("审批记录添加失败！报损数量不能小于0！");
        }
        goods.setInventoryQuantity(goods.getInventoryQuantity() - num);
        AssertUtils.isTrue(!(goodsService.updateById(goods)),"报损记录审批失败！");
        DamageList damageList=damageListService.getById(damageListId);
        damageList.setRemarks("1");
        AssertUtils.isTrue(!(damageListService.updateById(damageList)),"报损记录审批失败！");
        return RespBean.success("商品报损审批成功！");
    }





}
