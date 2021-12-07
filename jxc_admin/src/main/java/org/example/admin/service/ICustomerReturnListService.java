package org.example.admin.service;

import org.example.admin.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.ReturnQuery;

import java.util.Map;

/**
 * <p>
 * 客户退货单表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface ICustomerReturnListService extends IService<CustomerReturnList> {

    Map<String, Object> returnList(ReturnQuery returnQuery);
}
