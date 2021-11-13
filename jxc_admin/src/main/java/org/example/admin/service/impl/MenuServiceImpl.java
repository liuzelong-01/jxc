package org.example.admin.service.impl;

import org.example.admin.dto.TreeDto;
import org.example.admin.pojo.Menu;
import org.example.admin.mapper.MenuMapper;
import org.example.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<TreeDto> queryAllMenus() {
        return this.baseMapper.queryAllMenus();
    }
}
