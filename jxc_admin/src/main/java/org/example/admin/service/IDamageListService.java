package org.example.admin.service;

import org.example.admin.pojo.DamageList;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.ReturnQuery;

import java.util.Map;

/**
 * <p>
 * 报损单表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface IDamageListService extends IService<DamageList> {

    Map<String, Object> returnList(ReturnQuery returnQuery);

    Map<String, Object> returnAllList(ReturnQuery returnQuery);
}
