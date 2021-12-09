package org.example.admin.service;

import org.example.admin.pojo.SaleListGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 销售单商品表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface ISaleListGoodsService extends IService<SaleListGoods> {

    List<String> getGoodsName();


    Float getGoodsSaleSum(String x);

    Float getGoodsSaleMoneySum(String x);
}
