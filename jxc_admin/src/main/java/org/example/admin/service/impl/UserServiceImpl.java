package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.example.admin.pojo.User;
import org.example.admin.mapper.UserMapper;
import org.example.admin.pojo.UserRole;
import org.example.admin.query.UserQuery;
import org.example.admin.service.IRoleService;
import org.example.admin.service.IUserRoleService;
import org.example.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.AssertUtils;
import org.example.admin.utils.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-11-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRoleService userRoleService;


    @Override
    public User findUserByUserName(String userName) {
        return this.baseMapper.selectOne(new QueryWrapper<User>().eq("is_del",0).eq("user_name",userName));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUserInfo(User user) {
        AssertUtils.isTure(StringUtils.isEmpty(user.getUsername()),"用户名不能为空！");
        User temp =this.findUserByUserName(user.getUsername());
        AssertUtils.isTure( null!=temp&&!(temp.getId().equals(user.getId())),"用户名已存在");
        AssertUtils.isTure( !this.updateById(user),"用户信息更新失败！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword) {
        AssertUtils.isTure(null==this.findUserByUserName(userName),"用户不存在或未登录！");
        AssertUtils.isTure(StringUtils.isEmpty(oldPassword),"请输入原始密码！");
        AssertUtils.isTure(StringUtils.isEmpty(newPassword),"请输入新密码！");
        AssertUtils.isTure(StringUtils.isEmpty(confirmPassword),"请输入确认密码！");
        User user=this.findUserByUserName(userName);
        AssertUtils.isTure(!(passwordEncoder.matches(oldPassword,user.getPassword())),"原始密码输入错误！");
        AssertUtils.isTure(!(newPassword.equals(confirmPassword)),"新密码与确认密码不一致");
        AssertUtils.isTure(newPassword.equals(oldPassword),"新密码与原始密码不能一致");
        user.setPassword(passwordEncoder.encode(newPassword));
        AssertUtils.isTure(!this.updateById(user),"密码更新失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Map<String, Object> userList(UserQuery userQuery) {
        IPage<User> page=new Page<>(userQuery.getPage(),userQuery.getLimit());
       QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
       queryWrapper.eq("is_del",0);
       if (StringUtils.isNotBlank(userQuery.getUserName())){
           queryWrapper.like("user_name",userQuery.getUserName());
       }
        page = this.baseMapper.selectPage(page, queryWrapper);
      return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveUser(User user) {
        AssertUtils.isTure(StringUtils.isBlank(user.getUsername()),"用户名不能为空！");
        AssertUtils.isTure(null!=this.findUserByUserName(user.getUsername()),"用户名已存在");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setIsDel(0);
        AssertUtils.isTure(!(this.save(user)),"用户记录添加失败！");

        User temp=this.findUserByUserName(user.getUsername());
        relationUserRole(temp.getId(),user.getRoleIds());

    }

    private void relationUserRole(Integer userId, String roleIds) {
        long count=  userRoleService.count(new QueryWrapper<UserRole>().eq("user_id",userId));
        if (count>0){
            AssertUtils.isTure(!(userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id",userId))),"用户角色分配失败！");
        }
        if (StringUtils.isNotBlank(roleIds)){
            List<UserRole> userRoles=new ArrayList<>();
            for (String s : roleIds.split(",")) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.parseInt(s));
                userRoles.add(userRole);
            }
            AssertUtils.isTure(!(userRoleService.saveBatch(userRoles)),"用户角色分配失败！");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUser(User user) {
        AssertUtils.isTure(StringUtils.isBlank(user.getUsername()),"用户名不能为空");
        User temp=this.findUserByUserName(user.getUsername());
        AssertUtils.isTure(null!=temp&&!(temp.getId().equals(user.getId())),"用户名已存在！");
        relationUserRole(user.getId(),user.getRoleIds());
        AssertUtils.isTure(!(this.updateById(user)),"用户记录更新失败！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteUser(Integer[] ids) {
        AssertUtils.isTure(null==ids||ids.length==0,"请选择待删除的记录id！");

       long count=userRoleService.count(new QueryWrapper<UserRole>().in("user_id", Arrays.asList(ids)));
        if (count>0){
         AssertUtils.isTure(!(userRoleService.remove(new QueryWrapper<UserRole>().in("user_id",Arrays.asList(ids)))),"用户记录删除失败");
        }

        List<User> users =new ArrayList<>();
        for (Integer id : ids) {
            User temp=this.getById(id);
            temp.setIsDel(1);
            users.add(temp);
        }
        AssertUtils.isTure(!(this.updateBatchById(users)),"用户记录删除失败！");

    }
}
