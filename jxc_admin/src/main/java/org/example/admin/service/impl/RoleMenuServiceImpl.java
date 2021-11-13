package org.example.admin.service.impl;

import org.example.admin.pojo.RoleMenu;
import org.example.admin.mapper.RoleMenuMapper;
import org.example.admin.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public List<Integer> queryRoleHasAllMenusByRoleId(Integer roleId) {
        return this.baseMapper.queryRoleHasAllMenusByRoleId(roleId);
    }

    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleNames) {
        return this.baseMapper.findAuthoritiesByRoleName(roleNames);
    }
}
