package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.admin.pojo.CustomerReturnList;
import org.example.admin.mapper.CustomerReturnListMapper;
import org.example.admin.pojo.CustomerReturnListGoods;
import org.example.admin.pojo.SaleListGoods;
import org.example.admin.query.ReturnQuery;
import org.example.admin.service.ICustomerReturnListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 客户退货单表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
@Service
public class CustomerReturnListServiceImpl extends ServiceImpl<CustomerReturnListMapper, CustomerReturnList> implements ICustomerReturnListService {

    @Override
    public Map<String, Object> returnList(ReturnQuery returnQuery) {
        IPage<CustomerReturnListGoods> page=new Page<>(returnQuery.getPage(),returnQuery.getLimit());
        page=this.baseMapper.queryCustomerReturnListByparams(page,returnQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
