package org.example.admin.service;

import org.example.admin.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.RoleQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
public interface IRoleService extends IService<Role> {

    Map<String, Object> roleList(RoleQuery roleQuery);

    void saveRole(Role role);


    void updateRole(Role role);

    Role findRoleByRoleName(String roleName);

    void deleteRole(Integer id);

    List<Map<String, Object>> queryAllRoles(Integer userId);
}
