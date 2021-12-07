package org.example.admin.service;

import org.example.admin.pojo.ReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.PurchaseQuery;
import org.example.admin.query.ReturnQuery;

import java.util.Map;

/**
 * <p>
 * 退货单表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface IReturnListService extends IService<ReturnList> {

    Map<String, Object> returnList(ReturnQuery returnQuery);
}
