package org.example.admin.service;

import org.example.admin.pojo.SaleList;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.SaleQuery;

import java.util.Map;

/**
 * <p>
 * 销售单表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-12-07
 */
public interface ISaleListService extends IService<SaleList> {

    Map<String, Object> saleList(SaleQuery saleQuery);
}
