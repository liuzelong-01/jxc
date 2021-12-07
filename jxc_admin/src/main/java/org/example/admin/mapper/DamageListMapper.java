package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.admin.pojo.DamageList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.admin.pojo.DamageListGoods;
import org.example.admin.query.ReturnQuery;

/**
 * <p>
 * 报损单表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface DamageListMapper extends BaseMapper<DamageList> {

    IPage<DamageListGoods> queryDamageListByparams(IPage<DamageListGoods> page, ReturnQuery returnQuery);

    IPage<DamageListGoods> queryDamageAllListByparams(IPage<DamageListGoods> page, ReturnQuery returnQuery);
}
