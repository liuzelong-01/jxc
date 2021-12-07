package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.admin.pojo.PurchaseListGoods;
import org.example.admin.pojo.ReturnList;
import org.example.admin.mapper.ReturnListMapper;
import org.example.admin.pojo.ReturnListGoods;
import org.example.admin.query.ReturnQuery;
import org.example.admin.service.IReturnListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 退货单表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
@Service
public class ReturnListServiceImpl extends ServiceImpl<ReturnListMapper, ReturnList> implements IReturnListService {

    @Override
    public Map<String, Object> returnList(ReturnQuery returnQuery) {
        IPage<ReturnListGoods> page=new Page<>(returnQuery.getPage(),returnQuery.getLimit());
        page=this.baseMapper.queryReturnListByparams(page,returnQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
