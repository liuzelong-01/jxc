package org.example.admin.mapper;

import org.example.admin.dto.TreeDto;
import org.example.admin.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<TreeDto> queryAllMenus();
}
