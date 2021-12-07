package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.admin.pojo.SaleList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.admin.pojo.SaleListGoods;
import org.example.admin.query.SaleQuery;

/**
 * <p>
 * 销售单表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface SaleListMapper extends BaseMapper<SaleList> {
    IPage<SaleListGoods> querySaleListByparams(IPage<SaleListGoods> page, SaleQuery saleQuery);
}
