package org.example.admin.mapper;

import org.example.admin.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {


    List<String> findRolesByUserName(String userName);
}
