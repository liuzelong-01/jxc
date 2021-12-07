package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.admin.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.admin.pojo.CustomerReturnListGoods;
import org.example.admin.query.ReturnQuery;

/**
 * <p>
 * 客户退货单表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface CustomerReturnListMapper extends BaseMapper<CustomerReturnList> {

    IPage<CustomerReturnListGoods> queryCustomerReturnListByparams(IPage<CustomerReturnListGoods> page, ReturnQuery returnQuery);
}
