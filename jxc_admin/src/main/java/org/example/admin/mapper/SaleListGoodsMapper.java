package org.example.admin.mapper;

import org.example.admin.pojo.SaleListGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 销售单商品表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface SaleListGoodsMapper extends BaseMapper<SaleListGoods> {

    List<String> selectGoodsName();

    Float getSaleSum(String x);

    Float getSaleMoneySum(String x);
}
