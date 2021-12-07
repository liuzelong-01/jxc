package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.example.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.admin.pojo.PurchaseListGoods;
import org.example.admin.query.PurchaseQuery;

/**
 * <p>
 * 进货单 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-12-05
 */
public interface PurchaseListMapper extends BaseMapper<PurchaseList> {

    IPage<PurchaseListGoods> queryPurchaseListByparams(IPage<PurchaseListGoods> page,@Param("purchaseQuery") PurchaseQuery purchaseQuery);
}
