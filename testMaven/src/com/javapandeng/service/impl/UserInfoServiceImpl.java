package com.javapandeng.service.impl;



import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.OrderMapper;
import com.javapandeng.mapper.UserInfoMapper;
import com.javapandeng.po.Order;
import com.javapandeng.po.UserInfo;
import com.javapandeng.service.OrderService;
import com.javapandeng.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public BaseDao<UserInfo> getBaseDao() {
        return userInfoMapper;
    }
}
