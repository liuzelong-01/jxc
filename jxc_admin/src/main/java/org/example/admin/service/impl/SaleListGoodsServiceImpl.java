package org.example.admin.service.impl;



import org.example.admin.pojo.SaleListGoods;
import org.example.admin.mapper.SaleListGoodsMapper;
import org.example.admin.service.ISaleListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 销售单商品表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
@Service
public class SaleListGoodsServiceImpl extends ServiceImpl<SaleListGoodsMapper, SaleListGoods> implements ISaleListGoodsService {


    @Override
    public List<String> getGoodsName() {
        List<String> nameList=new ArrayList<>();
        nameList=this.baseMapper.selectGoodsName();
        return nameList;
    }

    @Override
    public Float getGoodsSaleSum(String x) {
        Float sum=this.baseMapper.getSaleSum(x);
        return sum;
    }

    @Override
    public Float getGoodsSaleMoneySum(String x) {
        Float sum=this.baseMapper.getSaleMoneySum(x);
        return sum;
    }
}
