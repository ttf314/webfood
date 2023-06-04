package com.javapandeng.service.impl;



import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.Order2Mapper;
import com.javapandeng.mapper.OrderMapper;
import com.javapandeng.po.Order;
import com.javapandeng.po.Order2;
import com.javapandeng.service.Order2Service;
import com.javapandeng.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Order2ServiceImpl extends BaseServiceImpl<Order2> implements Order2Service {
    @Autowired
    Order2Mapper order2Mapper;

    @Override
    public BaseDao<Order2> getBaseDao() {
        return order2Mapper;
    }
}
