package com.eaglediao.qa.service.impl;

import com.eaglediao.qa.dao.QuestionDao;
import com.eaglediao.qa.domain.Question;
import com.eaglediao.qa.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    public QuestionDao questionDao;

    @Transactional
    public Question getQuestion() throws Exception {
        Random random = new Random();
        return questionDao.getQuestionByID(random.nextInt(questionDao.getQuestionCount()-2)+1);
    }

    @Override
    @Transactional
    public List<Question> getQuestionList( int count) throws Exception {
        int questionCount = questionDao.getQuestionCount();
        if(count<questionCount)
            return questionDao.getQuestionList(new Random().nextInt(questionCount-count-1),count);
        return null;
    }
}