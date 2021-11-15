package org.example.admin.controller;


import org.example.admin.dto.TreeDto;
import org.example.admin.model.RespBean;
import org.example.admin.pojo.GoodsType;
import org.example.admin.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类别表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-11-15
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Autowired
    private IGoodsTypeService goodsTypeService;

    @RequestMapping("index")
    public String index(){
        return "goodsType/goods_type";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> goodsTypeList(){
        return goodsTypeService.goodsTypeList();
    }


    @RequestMapping("queryAllGoodsTypes")
    @ResponseBody
    public List<TreeDto> queryAllGoodsTypes(Integer typeId){
        return goodsTypeService.queryAllGoodsTypes(typeId);
    }


    @RequestMapping("addGoodsTypePage")
    public String addGoodsTypePage(Integer pId, Model model){
        model.addAttribute("pId",pId);
        return "goodsType/add";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean saveGoodsType(GoodsType goodsType){
        goodsTypeService.saveGoodsType(goodsType);
        return RespBean.success("商品类别添加成功！");
    }

    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteGoodsType(Integer id){
        goodsTypeService.deleteGoodsType(id);
        return RespBean.success("商品类别删除成功！");
    }


}
