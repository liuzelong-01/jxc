package org.example.admin.service;

import org.example.admin.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.GoodsQuery;

import java.util.Map;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-11-15
 */
public interface IGoodsService extends IService<Goods> {

    Map<String, Object> goodsList(GoodsQuery goodsQuery);

    String genGoodsCode();

    void saveGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoods(Integer id);

    void updateStock(Goods goods);

    void deleteStock(Integer id);
}
