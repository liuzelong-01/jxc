package org.example.admin.mapper;

import org.example.admin.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Map<String, Object>> queryAllRoles(Integer userId);
}
