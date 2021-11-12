package org.example.admin.service.impl;

import org.example.admin.pojo.UserRole;
import org.example.admin.mapper.UserRoleMapper;
import org.example.admin.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
