package org.example.admin.service;

import org.example.admin.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
public interface IUserRoleService extends IService<UserRole> {

    List<String> findRolesByUserName(String userName);
}
