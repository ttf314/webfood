package com.javapandeng.service.impl;



import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.ManageMapper;
import com.javapandeng.mapper.MealsMapper;
import com.javapandeng.po.Manage;
import com.javapandeng.po.Meals;
import com.javapandeng.service.ManageService;
import com.javapandeng.service.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealsServiceImpl extends BaseServiceImpl<Meals> implements MealsService {
    @Autowired
    MealsMapper mealsMapper;

    @Override
    public BaseDao<Meals> getBaseDao() {
        return mealsMapper;
    }
}
