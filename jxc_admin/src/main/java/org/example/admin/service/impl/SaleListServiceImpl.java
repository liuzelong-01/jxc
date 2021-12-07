package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.admin.pojo.ReturnListGoods;
import org.example.admin.pojo.SaleList;
import org.example.admin.mapper.SaleListMapper;
import org.example.admin.pojo.SaleListGoods;
import org.example.admin.query.SaleQuery;
import org.example.admin.service.ISaleListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 销售单表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
@Service
public class SaleListServiceImpl extends ServiceImpl<SaleListMapper, SaleList> implements ISaleListService {

    @Override
    public Map<String, Object> saleList(SaleQuery saleQuery) {
        IPage<SaleListGoods> page=new Page<>(saleQuery.getPage(),saleQuery.getLimit());
        page=this.baseMapper.querySaleListByparams(page,saleQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
