package org.example.admin.service;

import org.example.admin.dto.TreeDto;
import org.example.admin.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-11-12
 */
public interface IMenuService extends IService<Menu> {

    List<TreeDto> queryAllMenus(Integer roleId);
}
