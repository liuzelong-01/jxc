package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.example.admin.pojo.Customer;
import org.example.admin.mapper.CustomerMapper;
import org.example.admin.query.CustomerQuery;
import org.example.admin.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.AssertUtils;
import org.example.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-11-14
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Override
    public Map<String, Object> customerList(CustomerQuery customerQuery) {
        IPage<Customer> page=new Page<>(customerQuery.getPage(),customerQuery.getLimit());
        QueryWrapper<Customer> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_del",0);
        if (StringUtils.isNotBlank(customerQuery.getCustomerName())){
            queryWrapper.like("name",customerQuery.getCustomerName());
        }
        page=this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveCustomer(Customer customer) {
        checkParams(customer.getName(),customer.getContact(),customer.getNumber());
        AssertUtils.isTrue(null!=this.findCustomerByName(customer.getName()),"客户已存在！");
        customer.setIsDel(0);
        AssertUtils.isTrue(!(this.save(customer)),"客户记录添加失败！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateCustomer(Customer customer) {
        AssertUtils.isTrue(null==this.getById( customer.getId()),"请选择客户记录！");
        checkParams( customer.getName(), customer.getContact(), customer.getNumber());
        Customer temp=this.findCustomerByName( customer.getName());
        AssertUtils.isTrue(null!=temp&&!(temp.getId().equals( customer.getId())),"客户已存在！");
        AssertUtils.isTrue(!(this.updateById( customer)),"客户记录更新失败！");
    }

    @Override
    public Customer findCustomerByName(String name) {
        return this.getOne(new QueryWrapper<Customer>().eq("is_del",0).eq("name",name));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteCustomer(Integer[] ids) {
        AssertUtils.isTrue(null==ids||ids.length==0,"请选择待删除记录ID！");
        List<Customer> customerList=new ArrayList<>();

        for (Integer id : ids) {
            Customer temp=this.getById(id);
            temp.setIsDel(1);
            customerList.add(temp);
        }

        AssertUtils.isTrue(!(this.updateBatchById(customerList)),"客户记录删除失败");

    }


    private void checkParams(String name, String contact, String number) {

        AssertUtils.isTrue(StringUtils.isBlank(name),"请输入客户名称！");
        AssertUtils.isTrue(StringUtils.isBlank(contact),"请输入客户联系人！");
        AssertUtils.isTrue(StringUtils.isBlank(number),"请输入客户联系电话！");

    }

}
