package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.admin.pojo.User;
import org.example.admin.mapper.UserMapper;
import org.example.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.AssertUtils;
import org.example.admin.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public User login(String userName, String password) {
        AssertUtils.isTure(StringUtils.isEmpty(userName),"用户名不能为空");
        AssertUtils.isTure(StringUtils.isEmpty(password),"密码不能为空");
        User user=this.findUserByUserName(userName);
        AssertUtils.isTure(null==user,"该用户记录不存在或已经注销！");

        AssertUtils.isTure(!user.getPassword().equals(password),"密码错误");


        return user;
    }

    @Override
    public User findUserByUserName(String userName) {
        return this.baseMapper.selectOne(new QueryWrapper<User>().eq("is_del",0).eq("user_name",userName));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUserInfo(User user) {
        AssertUtils.isTure(StringUtils.isEmpty(user.getUserName()),"用户名不能为空！");
        User temp =this.findUserByUserName(user.getUserName());
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
        AssertUtils.isTure(!(user.getPassword().equals(oldPassword)),"原始密码输入错误！");
        AssertUtils.isTure(!(newPassword.equals(confirmPassword)),"新密码与确认密码不一致");
        AssertUtils.isTure(newPassword.equals(oldPassword),"新密码与原始密码不能一致");
        user.setPassword(newPassword);
        AssertUtils.isTure(!this.updateById(user),"密码更新失败");
    }
}