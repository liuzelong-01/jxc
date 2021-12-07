package org.example.admin.controller;


import org.example.admin.model.RespBean;
import org.example.admin.pojo.Customer;
import org.example.admin.pojo.Supplier;
import org.example.admin.query.CustomerQuery;
import org.example.admin.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author lzl
 * @since 2021-11-14
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @RequestMapping("/index")
    public String index(){
        return "customer/customer";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> customerList(CustomerQuery customerQuery){
        return customerService.customerList(customerQuery);
    }

    @RequestMapping("allCustomer")
    @ResponseBody
    public List<Customer> allCustomer(){
        return customerService.list();
    }

    @RequestMapping("addOrUpdateCustomerPage")
    public String addOrUpdateSupplierPage(Integer id, Model model){

        if (null!=id){
            model.addAttribute("customer",customerService.getById(id));
        }
        return "customer/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean saveCustomer(Customer customer){
        customerService.saveCustomer(customer);
        return RespBean.success("客户记录添加成功！");
    }

    @RequestMapping("update")
    @ResponseBody
    public RespBean updateCustomer(Customer customer){
        customerService.updateCustomer(customer);
        return RespBean.success("客户记录更新成功！");
    }

    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteCustomer(Integer[] ids){
        customerService.deleteCustomer(ids);
        return RespBean.success("客户记录删除成功！");
    }


}
