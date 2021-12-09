package org.example.admin.service;

import org.example.admin.pojo.PurchaseListGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 进货单商品表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-12-06
 */
public interface IPurchaseListGoodsService extends IService<PurchaseListGoods> {

    List<String> getGoodsName();

    Float getGoodsNum(String x);

    Float getGoodsMoney(String x);
}
