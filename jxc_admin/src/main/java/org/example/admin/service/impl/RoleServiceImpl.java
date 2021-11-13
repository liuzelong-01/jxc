package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.example.admin.pojo.Role;
import org.example.admin.mapper.RoleMapper;
import org.example.admin.pojo.RoleMenu;
import org.example.admin.pojo.User;
import org.example.admin.query.RoleQuery;
import org.example.admin.query.UserQuery;
import org.example.admin.service.IRoleMenuService;
import org.example.admin.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.AssertUtils;
import org.example.admin.utils.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private IRoleMenuService roleMenuService;


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        IPage<Role> page=new Page<>(roleQuery.getPage(),roleQuery.getLimit());
        QueryWrapper<Role> queryWrapper=new QueryWrapper<Role>();
        queryWrapper.eq("is_del",0);
        if (StringUtils.isNotBlank(roleQuery.getRoleName())){
            queryWrapper.like("name",roleQuery.getRoleName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveRole(Role role) {
        AssertUtils.isTure(StringUtils.isBlank(role.getName()),"请输入角色名！");
        AssertUtils.isTure(null!=this.findRoleByRoleName(role.getName()),"角色名已存在！");
        role.setIsDel(0);
        AssertUtils.isTure(!(this.save(role)),"角色添加失败！");

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateRole(Role role) {
        AssertUtils.isTure(StringUtils.isBlank(role.getName()),"请输入角色名！");
        Role temp=this.findRoleByRoleName(role.getName());
        AssertUtils.isTure(null!=temp&&!(temp.getId().equals(role.getId())),"角色名已存在！");
        AssertUtils.isTure(!(this.updateById(role)),"角色更新失败！");
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return this.baseMapper.selectOne(new QueryWrapper<Role>().eq("is_del",0).eq("name",roleName));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteRole(Integer id) {
        AssertUtils.isTure(null==id,"请选择待删除记录！");
        Role role=this.getById(id);
        AssertUtils.isTure(null==role,"待删除记录不存在！");
        role.setIsDel(1);
        AssertUtils.isTure(!(this.updateById(role)),"角色记录删除失败！");
    }

    @Override
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return this.baseMapper.queryAllRoles(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addGrant(Integer roleId, Integer[] mids) {
        Role role=this.getById(roleId);
        AssertUtils.isTure(null==role,"待授权角色记录不存在！");
        int count = (int) roleMenuService.count(new QueryWrapper<RoleMenu>().eq("role_id",roleId));
        if (count>0){
           AssertUtils.isTure(!( roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("role_id",roleId))),"角色授权失败！");
        }
        if(null!=mids&&mids.length>0){
            List<RoleMenu> roleMenus=new ArrayList<>();
            for (Integer mid : mids) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(mid);
                roleMenus.add(roleMenu);
            }

            AssertUtils.isTure(!(roleMenuService.saveBatch(roleMenus)),"角色授权失败！");
        }

    }
}
