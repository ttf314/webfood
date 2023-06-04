
package com.javapandeng.service.impl;



import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.EmployeeinfoMapper;
import com.javapandeng.mapper.SalaryandattendinfoMapper;
import com.javapandeng.po.Employeeinfo;
import com.javapandeng.po.Salaryandattendinfo;
import com.javapandeng.service.EmployeeinfoService;
import com.javapandeng.service.SalaryandattendinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryandattendinfoServiceImpl extends BaseServiceImpl<Salaryandattendinfo> implements SalaryandattendinfoService {
    @Autowired
    SalaryandattendinfoMapper salaryandattendinfoMapper;

    @Override
    public BaseDao<Salaryandattendinfo> getBaseDao() {
        return salaryandattendinfoMapper;
    }
}

