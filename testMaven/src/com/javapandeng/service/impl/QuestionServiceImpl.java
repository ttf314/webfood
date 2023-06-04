package com.javapandeng.service.impl;



import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.Order2Mapper;
import com.javapandeng.mapper.QuestionMapper;
import com.javapandeng.po.Order2;
import com.javapandeng.po.Question;
import com.javapandeng.service.Order2Service;
import com.javapandeng.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public BaseDao<Question> getBaseDao() {
        return questionMapper;
    }
}
