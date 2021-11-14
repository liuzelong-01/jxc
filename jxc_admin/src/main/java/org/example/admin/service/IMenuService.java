package org.example.admin.service;

import org.example.admin.dto.TreeDto;
import org.example.admin.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

    Map<String, Object> menuList();

    Menu findMenuByNameAndGrade(String menuName,Integer grade);

    Menu findMenuByAclValue(String aclValue);

    Menu findMenuById(Integer id);

    Menu findMenuByGradeAndUrl(String url,Integer grade);

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenuById(Integer id);
}
