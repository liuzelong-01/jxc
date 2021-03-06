package org.example.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.admin.pojo.PurchaseListGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 进货单商品表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-12-06
 */
@Mapper
public interface PurchaseListGoodsMapper extends BaseMapper<PurchaseListGoods> {


    List<String> selectGoodsName();

    Float selectGoodsNum(String x);

    Float selectGoodsMoney(String x);
}
