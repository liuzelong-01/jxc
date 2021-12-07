package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.admin.pojo.CustomerReturnListGoods;
import org.example.admin.pojo.DamageList;
import org.example.admin.mapper.DamageListMapper;
import org.example.admin.pojo.DamageListGoods;
import org.example.admin.query.ReturnQuery;
import org.example.admin.service.IDamageListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 报损单表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
@Service
public class DamageListServiceImpl extends ServiceImpl<DamageListMapper, DamageList> implements IDamageListService {

    @Override
    public Map<String, Object> returnList(ReturnQuery returnQuery) {
        IPage<DamageListGoods> page=new Page<>(returnQuery.getPage(),returnQuery.getLimit());
        page=this.baseMapper.queryDamageListByparams(page,returnQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    public Map<String, Object> returnAllList(ReturnQuery returnQuery) {
        IPage<DamageListGoods> page=new Page<>(returnQuery.getPage(),returnQuery.getLimit());
        page=this.baseMapper.queryDamageAllListByparams(page,returnQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
