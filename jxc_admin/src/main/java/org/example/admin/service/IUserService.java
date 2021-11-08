package org.example.admin.service;

import org.example.admin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-11-07
 */
public interface IUserService extends IService<User> {
    /**
     * 用户登录方法
     * @param userName
     * @param password
     * @return
     */
    User login(String userName,String password);

    /**
     * 根据用户名查询用户记录
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUserInfo(User user);

    void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword);
}
