package org.example.admin.service.impl;

import org.example.admin.pojo.PurchaseListGoods;
import org.example.admin.mapper.PurchaseListGoodsMapper;
import org.example.admin.service.IPurchaseListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.AssertUtils;
import org.junit.jupiter.api.AfterAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 进货单商品表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-12-06
 */
@Service
public class PurchaseListGoodsServiceImpl extends ServiceImpl<PurchaseListGoodsMapper, PurchaseListGoods> implements IPurchaseListGoodsService {

    @Override
    public List<String> getGoodsName() {
        List<String> nameList=new ArrayList<>();
        return this.baseMapper.selectGoodsName();
    }

    @Override
    public Float getGoodsNum(String x) {
        return this.baseMapper.selectGoodsNum(x);
    }

    @Override
    public Float getGoodsMoney(String x) {
        return this.baseMapper.selectGoodsMoney(x);
    }
}
