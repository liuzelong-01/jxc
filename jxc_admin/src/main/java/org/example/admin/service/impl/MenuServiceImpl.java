package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.example.admin.dto.TreeDto;
import org.example.admin.pojo.Menu;
import org.example.admin.mapper.MenuMapper;
import org.example.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public List<TreeDto> queryAllMenus(Integer roleId) {
        List<TreeDto> treeDtos =this.baseMapper.queryAllMenus();
        List<Integer> roleHasMenuIds=roleMenuService.queryRoleHasAllMenusByRoleId(roleId);
        if(CollectionUtils.isNotEmpty(roleHasMenuIds)){
            treeDtos.forEach(treeDto ->{
                if (roleHasMenuIds.contains(treeDto.getId())){
                    treeDto.setChecked(true);
                }
            });
        }
        return treeDtos;
    }
}
