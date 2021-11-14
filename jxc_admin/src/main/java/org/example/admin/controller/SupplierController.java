package org.example.admin.controller;


import org.example.admin.model.RespBean;
import org.example.admin.pojo.Supplier;
import org.example.admin.query.SupplierQuery;
import org.example.admin.query.UserQuery;
import org.example.admin.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 * 供应商表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-11-14
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;


    @RequestMapping("index")
    public String index(){
        return "supplier/supplier";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> supplierList(SupplierQuery supplierQuery){
        return supplierService.supplierList(supplierQuery);
    }

    @RequestMapping("addOrUpdateSupplierPage")
    public String addOrUpdateSupplierPage(Integer id, Model model){
        if (null!=id){
            model.addAttribute("supplier",supplierService.getById(id));
        }
        return "supplier/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean saveSupplier(Supplier supplier){
        supplierService.saveSupplier(supplier);
        return RespBean.success("供应商记录添加成功！");
    }

    @RequestMapping("update")
    @ResponseBody
    public RespBean updateSupplier(Supplier supplier){
        supplierService.updateSupplier(supplier);
        return RespBean.success("供应商记录更新成功！");
    }

    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteSupplier(Integer[] ids){
        supplierService.deleteSupplier(ids);
        return RespBean.success("供应商记录删除成功！");
    }

}
