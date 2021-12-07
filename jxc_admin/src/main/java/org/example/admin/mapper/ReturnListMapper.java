package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.admin.pojo.ReturnList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.admin.pojo.ReturnListGoods;
import org.example.admin.query.ReturnQuery;

/**
 * <p>
 * 退货单表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface ReturnListMapper extends BaseMapper<ReturnList> {

    IPage<ReturnListGoods> queryReturnListByparams(IPage<ReturnListGoods> page, ReturnQuery returnQuery);
}
