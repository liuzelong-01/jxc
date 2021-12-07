package org.example.admin.service;

import org.example.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.PurchaseQuery;

import java.util.Map;

/**
 * <p>
 * 进货单 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-12-05
 */
public interface IPurchaseListService extends IService<PurchaseList> {

    Map<String, Object> purchaseList(PurchaseQuery purchaseQuery);
}
