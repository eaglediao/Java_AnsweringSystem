package com.eaglediao.qa.dao;

import com.eaglediao.qa.domain.Question;

import java.util.List;

public interface QuestionDao {

    Question getQuestionByType(String type) throws Exception;
    Question getQuestionByID(int id) throws Exception;
    int getQuestionCount() throws Exception;
    List<Question> getQuestionList(int offset, int count);
}