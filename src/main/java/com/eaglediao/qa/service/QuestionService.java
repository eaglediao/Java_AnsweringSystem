package com.eaglediao.qa.service;

import com.eaglediao.qa.domain.Question;

import java.util.List;

public interface QuestionService {

    Question getQuestion() throws Exception;
    List<Question> getQuestionList(int count) throws Exception;
}