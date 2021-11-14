package org.example.admin.service;

import org.example.admin.pojo.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.query.SupplierQuery;

import java.util.Map;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author lzl
 * @since 2021-11-14
 */
public interface ISupplierService extends IService<Supplier> {

    Map<String, Object> supplierList(SupplierQuery supplierQuery);
    
    Supplier findSupplierByName(String name);

    void saveSupplier(Supplier supplier);

    void updateSupplier(Supplier supplier);

    void deleteSupplier(Integer[] ids);
}
