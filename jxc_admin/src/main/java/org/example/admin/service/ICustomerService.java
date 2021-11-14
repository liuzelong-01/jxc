package org.example.admin.service;

import org.example.admin.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.CustomerQuery;

import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-11-14
 */
public interface ICustomerService extends IService<Customer> {

    Map<String, Object> customerList(CustomerQuery customerQuery);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    Object findCustomerByName(String name);

    void deleteCustomer(Integer[] ids);
}
