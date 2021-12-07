package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.admin.pojo.PurchaseList;
import org.example.admin.mapper.PurchaseListMapper;
import org.example.admin.pojo.PurchaseListGoods;
import org.example.admin.query.PurchaseQuery;
import org.example.admin.service.IPurchaseListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 进货单 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-12-05
 */
@Service
public class PurchaseListServiceImpl extends ServiceImpl<PurchaseListMapper, PurchaseList> implements IPurchaseListService {

    @Override
    public Map<String, Object> purchaseList(PurchaseQuery purchaseQuery) {
        IPage<PurchaseListGoods> page=new Page<>(purchaseQuery.getPage(),purchaseQuery.getLimit());
        page=this.baseMapper.queryPurchaseListByparams(page,purchaseQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
