package org.example.admin.controller;


import net.sf.json.JSONObject;
import org.example.admin.pojo.EchartsVo;
import org.example.admin.service.IPurchaseListGoodsService;
import org.example.admin.service.ISaleListGoodsService;
import org.example.admin.service.ISaleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ISaleListGoodsService saleListGoodsService;

    @Autowired
    private ISaleListService saleListService;

    @Autowired
    private IPurchaseListGoodsService purchaseListGoodsService;

    @RequestMapping("/countSale")
    public String countSale(){
        return "report/countSale";
    }

    @RequestMapping("/countSaleMonth")
    public String countSaleMonth(){
        return "report/countSaleMonth";
    }

    @RequestMapping("/countSaleMoney")
    public String countSaleMoney(){
        return "report/countSaleMoney";
    }

    @RequestMapping("/countCustomer")
    public String countCustomer(){
        return "report/countCustomer";
    }

    @RequestMapping("/countPurchase")
    public String countPurchase(){
        return "report/countPurchase";
    }

    @RequestMapping("/countSaleMoneyDistribution")
    public String countMoneyDistribution(){
        return "report/countMoneyDistribution";
    }

    @RequestMapping("/countSaleDistribution")
    public String countSaleDistribution(){
        return "report/countSaleDistribution";
    }

    @RequestMapping("getSaleEchartsData")
    @ResponseBody
    public JSONObject getSaleEchartsData(){
        EchartsVo echartsVo=new EchartsVo();
        List<Float> yData=new ArrayList<>();
        List<String> xData= saleListGoodsService.getGoodsName();
        for (String x : xData) {
            //根据商品名来获取对应的销售金额
            Float count = saleListGoodsService.getGoodsSaleSum(x);
            //放入集合中
            yData.add(count);
        }
        echartsVo.setXData(xData);
        echartsVo.setYData(yData);
        return JSONObject.fromObject(echartsVo);

    }

    @RequestMapping("getSaleMoneyEchartsData")
    @ResponseBody
    public JSONObject getSaleMoneyEchartsData(){
        EchartsVo echartsVo=new EchartsVo();
        List<Float> yData=new ArrayList<>();
        List<String> xData= saleListGoodsService.getGoodsName();
        for (String x : xData) {
            //根据商品名来获取对应的销售金额
            Float count = saleListGoodsService.getGoodsSaleMoneySum(x);
            //放入集合中
            yData.add(count);
        }
        echartsVo.setXData(xData);
        echartsVo.setYData(yData);
        return JSONObject.fromObject(echartsVo);

    }

    @RequestMapping("getCustomerEchartsData")
    @ResponseBody
    public JSONObject getCustomerEchartsData(){
        EchartsVo echartsVo=new EchartsVo();
        List<Float> yData=new ArrayList<>();
        List<String> xData= saleListService.getCustomerName();
        for (String x : xData) {
            //根据商品名来获取对应的销售金额
            Float count = saleListService.getCustomerMoneySum(x);
            //放入集合中
            yData.add(count);
        }
        echartsVo.setXData(xData);
        echartsVo.setYData(yData);
        return JSONObject.fromObject(echartsVo);

    }

    @RequestMapping("getPurchaseNumEchartsData")
    @ResponseBody
    public JSONObject getPurchaseNumEchartsData(){
        EchartsVo echartsVo=new EchartsVo();
        List<Float> yData=new ArrayList<>();
        List<String> xData= purchaseListGoodsService.getGoodsName();
        for (String x : xData) {
            //根据商品名来获取对应的销售金额
            Float count = purchaseListGoodsService.getGoodsNum(x);
            //放入集合中
            yData.add(count);
        }
        echartsVo.setXData(xData);
        echartsVo.setYData(yData);
        return JSONObject.fromObject(echartsVo);

    }

    @RequestMapping("getPurchaseMoneyEchartsData")
    @ResponseBody
    public JSONObject getPurchaseMoneyEchartsData(){
        EchartsVo echartsVo=new EchartsVo();
        List<Float> yData=new ArrayList<>();
        List<String> xData= purchaseListGoodsService.getGoodsName();
        for (String x : xData) {
            //根据商品名来获取对应的销售金额
            Float count = purchaseListGoodsService.getGoodsMoney(x);
            //放入集合中
            yData.add(count);
        }
        echartsVo.setXData(xData);
        echartsVo.setYData(yData);
        return JSONObject.fromObject(echartsVo);

    }

    @RequestMapping("getSaleMonthEchartsData")
    @ResponseBody
    public JSONObject getSaleMonthEchartsData(){
        EchartsVo echartsVo=new EchartsVo();
        List<String> xData= saleListService.getSaleMonth();
        List<Float> yData= saleListService.getSaleMonthPay();
        echartsVo.setXData(xData);
        echartsVo.setYData(yData);
        return JSONObject.fromObject(echartsVo);

    }


}
