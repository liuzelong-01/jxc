package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.example.admin.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.admin.query.GoodsQuery;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-11-15
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    IPage<Goods> queryGoodsByParams(IPage<Goods> page,@Param("goodsQuery") GoodsQuery goodsQuery);
}
