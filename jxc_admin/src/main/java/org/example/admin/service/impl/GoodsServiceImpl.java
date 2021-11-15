package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.example.admin.pojo.Goods;
import org.example.admin.mapper.GoodsMapper;
import org.example.admin.query.GoodsQuery;
import org.example.admin.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.service.IGoodsTypeService;
import org.example.admin.utils.AssertUtils;
import org.example.admin.utils.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-11-15
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Autowired
    private IGoodsTypeService goodsTypeService;

    @Override
    public Map<String, Object> goodsList(GoodsQuery goodsQuery) {
        IPage<Goods> page=new Page<>(goodsQuery.getPage(),goodsQuery.getLimit());

        if (null!=goodsQuery.getTypeId()){
            goodsQuery.setTypeIds(goodsTypeService.queryAllSubTypeIdsByTypeId(goodsQuery.getTypeId()));
        }

        page=this.baseMapper.queryGoodsByParams(page,goodsQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    public String genGoodsCode() {
        String maxGoodsCode=this.baseMapper.selectOne(new QueryWrapper<Goods>().select("max(code) as code")).getCode();
        if (StringUtils.isNotEmpty(maxGoodsCode)){
            Integer code =Integer.valueOf(maxGoodsCode)+1;
            String codes =code.toString();
            int length=codes.length();
            for (int i=4;i>length;i--){
                codes="0"+codes;
            }
            return codes;
        }else {
            return "0001";
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveGoods(Goods goods) {
        AssertUtils.isTrue(StringUtils.isBlank(goods.getName()),"请指定商品名称！");
        AssertUtils.isTrue(null==goods.getTypeId(),"请指定商品类别！");
        AssertUtils.isTrue(StringUtils.isBlank(goods.getUnit()),"请指定商品单位！");
        goods.setCode(genGoodsCode());
        goods.setInventoryQuantity(0);
        goods.setState(0);
        goods.setLastPurchasingPrice(0F);
        goods.setIsDel(0);
        AssertUtils.isTrue(!(this.save(goods)),"记录添加失败！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateGoods(Goods goods) {
        AssertUtils.isTrue(StringUtils.isBlank(goods.getName()),"请指定商品名称！");
        AssertUtils.isTrue(null==goods.getTypeId(),"请指定商品类别！");
        AssertUtils.isTrue(StringUtils.isBlank(goods.getUnit()),"请指定商品单位！");
        AssertUtils.isTrue(!(this.updateById(goods)),"记录更新失败！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteGoods(Integer id) {
        Goods goods=this.getById(id);
        AssertUtils.isTrue(null==goods,"待删除的商品记录不存在！");
        AssertUtils.isTrue(goods.getState()==1,"该商品已经期初入库！");
        AssertUtils.isTrue(goods.getState()==2,"该商品已经产生单据，不能删除！");
        goods.setIsDel(1);
        AssertUtils.isTrue(!(this.updateById(goods)),"商品删除失败！");
    }

    @Override
    public void updateStock(Goods goods) {
        Goods temp =this.getById(goods.getId());
        AssertUtils.isTrue(null == goods,"待更新的商品记录不存在!");
        AssertUtils.isTrue(goods.getInventoryQuantity()<=0,"库存量必须>0");
        AssertUtils.isTrue(goods.getPurchasingPrice()<=0,"成本价必须>0");
        AssertUtils.isTrue(!(this.updateById(goods)),"商品更新失败!");
    }

    @Override
    public void deleteStock(Integer id) {
        Goods temp =this.getById(id);
        AssertUtils.isTrue(null == temp,"待更新的商品记录不存在!");
        AssertUtils.isTrue(temp.getState() == 2,"该商品已经发生单据，不可删除!");
        temp.setInventoryQuantity(0);
        AssertUtils.isTrue(!(this.updateById(temp)),"商品删除失败!");
    }
}
