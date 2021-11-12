package org.example.admin.service;

import org.example.admin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.UserQuery;

import java.util.Map;

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

    Map<String, Object> userList(UserQuery userQuery);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer[] ids);
}
