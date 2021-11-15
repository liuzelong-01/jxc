package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.admin.dto.TreeDto;
import org.example.admin.pojo.Menu;
import org.example.admin.mapper.MenuMapper;
import org.example.admin.pojo.RoleMenu;
import org.example.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.service.IRoleMenuService;
import org.example.admin.utils.AssertUtils;
import org.example.admin.utils.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> menuList() {
        Map<String,Object> result =new HashMap<>();
        List<Menu> menus=this.list(new QueryWrapper<Menu>().eq("is_del",0));
        return PageResultUtil.getResult((long) menus.size(),menus);
    }

    @Override
    public Menu findMenuByNameAndGrade(String menuName,Integer grade) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("name",menuName).eq("grade",grade));
    }

    @Override
    public Menu findMenuByAclValue(String aclValue) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("acl_value",aclValue));
    }

    @Override
    public Menu findMenuById(Integer id) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("id",id));
    }

    @Override
    public Menu findMenuByGradeAndUrl(String url, Integer grade) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("url",url).eq("grade",grade));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveMenu(Menu menu) {
        AssertUtils.isTrue(StringUtils.isBlank(menu.getName()),"请输入菜单名！");
        Integer grade=menu.getGrade();
        AssertUtils.isTrue(null==grade||!(grade==0||grade==1||grade==2),"菜单层级不合法！");
        AssertUtils.isTrue(null!=this.findMenuByNameAndGrade(menu.getName(),menu.getGrade()),"该层级下菜单已存在！");
        AssertUtils.isTrue(null!=this.findMenuByAclValue(menu.getAclValue()),"权限码不能重复！");
        AssertUtils.isTrue(null==menu.getPId()||null==this.findMenuById(menu.getPId()),"请指定上级菜单！");
        if (grade == 1) {
            AssertUtils.isTrue(null!=this.findMenuByGradeAndUrl(menu.getUrl(),menu.getGrade()),"该层级下URL不可重复！");
        }
        menu.setIsDel(0);
        menu.setState(0);
        AssertUtils.isTrue(!(this.save(menu)),"菜单添加失败！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateMenu(Menu menu) {
        AssertUtils.isTrue(null==menu.getId()||null==this.findMenuById(menu.getId()),"待更新的记录不存在！");
        AssertUtils.isTrue(StringUtils.isBlank(menu.getName()),"菜单名不能为空！");
        Integer grade=menu.getGrade();
        AssertUtils.isTrue(null==grade||!(grade==0||grade==1||grade==2),"菜单层级不合法！");
        Menu temp =this.findMenuByNameAndGrade(menu.getName(),menu.getGrade());
        AssertUtils.isTrue(null!=temp&&!(temp.getId().equals(menu.getId())),"该层级下菜单已存在！");
        temp=this.findMenuByAclValue(menu.getAclValue());
        AssertUtils.isTrue(null!=temp&&!(temp.getId().equals(menu.getId())),"权限码已存在！");
        AssertUtils.isTrue(null==menu.getPId()||null==this.findMenuById(menu.getPId()),"请指定上级菜单！");
        if (grade == 1) {
            temp=this.findMenuByGradeAndUrl(menu.getUrl(),menu.getGrade());
            AssertUtils.isTrue(null!=temp&&!(temp.getId().equals(menu.getId())),"该层级下URL不可重复！");
        }
        AssertUtils.isTrue(!(this.updateById(menu)),"菜单记录更新失败！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteMenuById(Integer id) {
        Menu menu=this.findMenuById(id);
        AssertUtils.isTrue(null==id||null==menu,"待删除的记录不存在！");
        int count= (int) this.count(new QueryWrapper<Menu>().eq("is_del",0).eq("p_id",id));
        AssertUtils.isTrue(count>0,"存在子菜单，不允许直接删除！");
        count= (int) roleMenuService.count(new QueryWrapper<RoleMenu>().eq("menu_id",id));
        if (count>0){
            AssertUtils.isTrue(!(roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("menu_id",id))),"菜单删除失败！");
        }
        menu.setIsDel(1);
        AssertUtils.isTrue(!(this.updateById(menu)),"菜单删除失败！");
    }
}
