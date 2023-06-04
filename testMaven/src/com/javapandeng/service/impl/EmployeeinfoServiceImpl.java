
package com.javapandeng.service.impl;



import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.EmployeeinfoMapper;
import com.javapandeng.mapper.UserInfoMapper;
import com.javapandeng.po.Employeeinfo;
import com.javapandeng.po.UserInfo;
import com.javapandeng.service.EmployeeinfoService;
import com.javapandeng.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeinfoServiceImpl extends BaseServiceImpl<Employeeinfo> implements EmployeeinfoService {
    @Autowired
    EmployeeinfoMapper employeeinfoMapper;

    @Override
    public BaseDao<Employeeinfo> getBaseDao() {
        return employeeinfoMapper;
    }
}

