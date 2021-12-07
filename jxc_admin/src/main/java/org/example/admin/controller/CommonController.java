package org.example.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {


    @RequestMapping("/toGoodsStockPage")
    public String index(){
        return "common/goodsStockPage";
    }

    @RequestMapping("/alarmPage")
    public String alarmPage(){
        return "common/alarmPage";
    }



}
