package org.example.admin.controller;


import org.example.admin.model.RespBean;
import org.example.admin.pojo.Goods;
import org.example.admin.query.GoodsQuery;
import org.example.admin.service.IGoodsService;
import org.example.admin.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-11-15
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IGoodsTypeService goodsTypeService;

        @RequestMapping("index")
        public String index(){
            return "goods/goods";
        }

        @RequestMapping("list")
        @ResponseBody
        public Map<String,Object> goodsList(GoodsQuery goodsQuery){
            return goodsService.goodsList(goodsQuery);
        }

        @RequestMapping("addOrUpdateGoodsPage")
        public String addOrUpdateGoodsPage(Integer id,Integer typeId,Model model){
            if (null!=id){
                Goods goods=goodsService.getById(id);
                model.addAttribute("goods",goods);
                model.addAttribute("goodsType",goodsTypeService.getById(goods.getTypeId()));
            }else {
                if (null!= typeId){
                    model.addAttribute("goodsType",goodsTypeService.getById(typeId));
                }
            }

            return "goods/add_update";
        }

        @RequestMapping("toGoodsTypePage")
        public String toGoodsTypePage(Integer typeId, Model model){
            if(null!=typeId){
                model.addAttribute("typeId",typeId);
            }
            return "goods/goods_type";
        }

        @RequestMapping("save")
        @ResponseBody
        public RespBean saveGoods(Goods goods){
            goodsService.saveGoods(goods);
            return RespBean.success("商品记录添加成功！");
        }

        @RequestMapping("update")
        @ResponseBody
        public RespBean updateGoods(Goods goods){
            goodsService.updateGoods(goods);
            return RespBean.success("商品记录更新成功！");
        }

        @RequestMapping("delete")
        @ResponseBody
        public RespBean deleteGoods(Integer id){
            goodsService.deleteGoods(id);
            return RespBean.success("商品记录删除成功！");
        }

}
