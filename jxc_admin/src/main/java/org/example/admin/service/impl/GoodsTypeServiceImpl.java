package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;
import org.example.admin.dto.TreeDto;
import org.example.admin.pojo.GoodsType;
import org.example.admin.mapper.GoodsTypeMapper;
import org.example.admin.service.IGoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.AssertUtils;
import org.example.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品类别表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-11-15
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements IGoodsTypeService {

    @Override
    public List<TreeDto> queryAllGoodsTypes(Integer typeId) {
        List<TreeDto> treeDtos =this.baseMapper.queryAllGoodsTypes();
        if (null!= typeId){
            for (TreeDto treeDto : treeDtos) {
                if (treeDto.getId().equals(typeId)){
                    treeDto.setChecked(true);
                    break;
                }
            }
        }
        return treeDtos;
    }

    @Override
    public List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId) {
        GoodsType goodsType=this.getById(typeId);
        if (goodsType.getPId()==-1){
            return this.list().stream().map(GoodsType::getId).collect(Collectors.toList());
        }
        List<Integer> results = new ArrayList<Integer>();
        if (goodsType.getPId()==1){
            QueryWrapper<GoodsType> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("p_id",goodsType.getId());
            results=this.baseMapper.selectList(queryWrapper).stream().map(GoodsType::getId).collect(Collectors.toList());
            return results;
        }
        else {
            results.add(typeId);
            return results;
        }
    }

    @Override
    public Map<String, Object> goodsTypeList() {
        List<GoodsType> menus = this.list();
        return PageResultUtil.getResult((long) menus.size(),menus);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveGoodsType(GoodsType goodsType) {
        AssertUtils.isTrue(StringUtils.isBlank(goodsType.getName()),"商品类别名称不能为空！");
        AssertUtils.isTrue(null==goodsType.getPId(),"请指定父类别！");
        goodsType.setState(0);
        AssertUtils.isTrue(!(this.save(goodsType)),"记录添加失败！");
        GoodsType parent=this.getById(goodsType.getPId());
        if (parent.getState()==0){
            parent.setState(1);
        }
        AssertUtils.isTrue(!(this.updateById(parent)),"记录添加失败！");
    }

    @Override
    public void deleteGoodsType(Integer id) {
        GoodsType temp =this.getById(id);
        AssertUtils.isTrue(null==temp,"待删除的记录不存在！");
        int count = (int) this.count(new QueryWrapper<GoodsType>().eq("p_id",id));
        AssertUtils.isTrue(count>0,"存在子类别，暂不支持删除！");
        count=countGoods(id);
        AssertUtils.isTrue(count>0,"该类别下存在商品，不能删除！");


        count= (int) this.count(new QueryWrapper<GoodsType>().eq("p_id",temp.getPId()));
        if (count==1){
            AssertUtils.isTrue(!(this.update(new UpdateWrapper<GoodsType>().set("state",0).eq("id",temp.getPId()))),"类别删除失败！");
        }

        AssertUtils.isTrue(!(this.removeById(id)),"类别删除失败！");
    }

    @Override
    public int countGoods(Integer id) {
     return this.baseMapper.countGoods(id);
    }
}
