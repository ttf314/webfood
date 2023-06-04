package com.javapandeng.service.impl;


import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.ItemCategoryMapper;
import com.javapandeng.mapper.Item_warehouseMapper;
import com.javapandeng.po.ItemCategory;
import com.javapandeng.po.Item_warehouse;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.service.Item_warehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Item_warehouseServiceImpl extends BaseServiceImpl<Item_warehouse> implements Item_warehouseService {
    @Autowired
    Item_warehouseMapper item_warehouseMapper;

    @Override
    public BaseDao<Item_warehouse> getBaseDao() {
        return item_warehouseMapper;
    }
}
