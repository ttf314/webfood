package com.javapandeng.service.impl;



import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.InboundMapper;
import com.javapandeng.mapper.UserInfoMapper;
import com.javapandeng.po.Inbound;
import com.javapandeng.po.UserInfo;
import com.javapandeng.service.InboundService;
import com.javapandeng.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InboundServiceImpl extends BaseServiceImpl<Inbound> implements InboundService {
    @Autowired
    InboundMapper inboundMapper;

    @Override
    public BaseDao<Inbound> getBaseDao() {
        return inboundMapper;
    }
}
