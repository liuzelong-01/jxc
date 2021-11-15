package org.example.admin.mapper;

import org.example.admin.dto.TreeDto;
import org.example.admin.pojo.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品类别表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-11-15
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    List<TreeDto> queryAllGoodsTypes();

    int countGoods(Integer id);
}
