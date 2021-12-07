package org.example.admin.controller;

import org.example.admin.model.RespBean;
import org.example.admin.pojo.Goods;
import org.example.admin.query.GoodsQuery;
import org.example.admin.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("stock")
public class StockController {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("index")
    public String index(){
        return "stock/stock";
    }

    @RequestMapping("listNoInventoryQuantity")
    @ResponseBody
    public Map<String,Object> listNoInventoryQuantity(GoodsQuery goodsQuery){
        goodsQuery.setType(1);
        return goodsService.goodsList(goodsQuery);
    }

    @RequestMapping("listHasInventoryQuantity")
    @ResponseBody
    public Map<String,Object> listHasInventoryQuantity(GoodsQuery goodsQuery){
        goodsQuery.setType(2);
        return goodsService.goodsList(goodsQuery);
    }

    @RequestMapping("listAllInventoryQuantity")
    @ResponseBody
    public Map<String,Object> listAllInventoryQuantity(GoodsQuery goodsQuery){
        goodsQuery.setType(3);
        return goodsService.goodsList(goodsQuery);
    }

    @RequestMapping("listAlarmInventoryQuantity")
    @ResponseBody
    public Map<String,Object> listAlarmInventoryQuantity(GoodsQuery goodsQuery){
        goodsQuery.setType(4);
        return goodsService.goodsList(goodsQuery);
    }

    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(Integer gid, Model model){
        model.addAttribute("goods",goodsService.getById(gid));
        return "stock/goods_update";
    }

    @RequestMapping("updateStock")
    @ResponseBody
    public RespBean updateStock(Goods goods){
        goodsService.updateStock(goods);
        return RespBean.success("商品记录更新成功!");
    }


    @RequestMapping("deleteStock")
    @ResponseBody
    public RespBean deleteStock(Integer id){
        goodsService.deleteStock(id);
        return RespBean.success("商品记录删除成功!");
    }


}
