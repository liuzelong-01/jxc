package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.example.admin.pojo.Supplier;
import org.example.admin.mapper.SupplierMapper;
import org.example.admin.query.SupplierQuery;
import org.example.admin.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.utils.AssertUtils;
import org.example.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2021-11-14
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Override
    public Map<String, Object> supplierList(SupplierQuery supplierQuery) {
        IPage<Supplier> page=new Page<>(supplierQuery.getPage(),supplierQuery.getLimit());
        QueryWrapper<Supplier> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_del",0);
        if (StringUtils.isNotBlank(supplierQuery.getSupplierName())){
            queryWrapper.like("name",supplierQuery.getSupplierName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    public Supplier findSupplierByName(String name) {
        return this.getOne(new QueryWrapper<Supplier>().eq("is_del",0).eq("name",name));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveSupplier(Supplier supplier) {
        checkParams(supplier.getName(),supplier.getContact(),supplier.getNumber());
        AssertUtils.isTure(null!=this.findSupplierByName(supplier.getName()),"供应商已存在！");
        supplier.setIsDel(0);
        AssertUtils.isTure(!(this.save(supplier)),"供应商记录添加失败！");

    }



    private void checkParams(String name, String contact, String number) {

        AssertUtils.isTure(StringUtils.isBlank(name),"请输入供应商名称！");
        AssertUtils.isTure(StringUtils.isBlank(contact),"请输入供应商联系人！");
        AssertUtils.isTure(StringUtils.isBlank(number),"请输入供应商联系电话！");

    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateSupplier(Supplier supplier) {
        AssertUtils.isTure(null==this.getById(supplier.getId()),"请选择供应商记录！");
        checkParams(supplier.getName(),supplier.getContact(),supplier.getNumber());
        Supplier temp=this.findSupplierByName(supplier.getName());
        AssertUtils.isTure(null!=temp&&!(temp.getId().equals(supplier.getId())),"供应商已存在！");
        AssertUtils.isTure(!(this.updateById(supplier)),"供应商记录更新失败！");


    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteSupplier(Integer[] ids) {
        AssertUtils.isTure(null==ids||ids.length==0,"请选择待删除记录ID！");
        List<Supplier> supplierList=new ArrayList<>();

        for (Integer id : ids) {
            Supplier temp=this.getById(id);
            temp.setIsDel(1);
            supplierList.add(temp);
        }

        AssertUtils.isTure(!(this.updateBatchById(supplierList)),"供应商记录删除失败");
    }

}
