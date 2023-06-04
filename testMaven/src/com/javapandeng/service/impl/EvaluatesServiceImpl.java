package com.javapandeng.service.impl;



import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.EvaluatesMapper;
import com.javapandeng.mapper.MealsMapper;
import com.javapandeng.po.Evaluates;
import com.javapandeng.po.Meals;
import com.javapandeng.service.EvaluatesService;
import com.javapandeng.service.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluatesServiceImpl extends BaseServiceImpl<Evaluates> implements EvaluatesService {
    @Autowired
    EvaluatesMapper evaluatesMapper;

    @Override
    public BaseDao<Evaluates> getBaseDao() {
        return evaluatesMapper;
    }
}
