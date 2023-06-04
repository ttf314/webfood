package com.javapandeng.service.impl;



import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.MealsMapper;
import com.javapandeng.mapper.OrderMapper;
import com.javapandeng.po.Meals;
import com.javapandeng.po.Order;
import com.javapandeng.service.MealsService;
import com.javapandeng.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public BaseDao<Order> getBaseDao() {
        return orderMapper;
    }
}
